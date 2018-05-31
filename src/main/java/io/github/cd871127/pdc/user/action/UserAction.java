package io.github.cd871127.pdc.user.action;


import io.github.cd871127.pdc.common.util.SystemConst;
import io.github.cd871127.pdc.common.util.encrypt.Base64URLSafe;
import io.github.cd871127.pdc.common.util.encrypt.RSAEncrypt;
import io.github.cd871127.pdc.user.dto.UserInfoDTO;
import io.github.cd871127.pdc.user.mapper.UserMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;

import static io.github.cd871127.pdc.common.util.SystemConst.RequestResult.OPERATION_FAILED;
import static io.github.cd871127.pdc.common.util.SystemConst.RequestResult.SUCCESS;


@Service
public class UserAction {

    @Resource
    private UserMapper userMapper;
    @Resource
    private RSAEncrypt rsaEncrypt;
    @Resource
    private RedisTemplate<String, KeyPair> redisTemplate;
    @Resource
    private Base64URLSafe base64URLSafe;

    public SystemConst.RequestResult deleteUserInfo(String userName) {
        if (userMapper.insertUserHisTbl(userName) &&
                userMapper.deleteUserInfoByUserName(userName))
            return SUCCESS;
        return OPERATION_FAILED;

    }

    public UserInfoDTO userLogin(String userName, String passWord, String keyId) {
        Map<String, String> paraMap = new HashMap<>();
        passWord = decode(keyId, passWord);
        paraMap.put("passWord", passWord);
        paraMap.put("userName",userName);
        return userMapper.getUserInfo(paraMap);
    }

    public SystemConst.RequestResult userRegister(UserInfoDTO userInfoDTO, String keyId) {
        //判断用户是否存在
        if (userMapper.isUserExist(userInfoDTO.getUserName())) {
            //存在则跳转到错误页面
            return SystemConst.RequestResult.USER_EXISTED;
        }

        // 解密密码
        String passWord = decode(keyId, userInfoDTO.getPassWord());
        userInfoDTO.setPassWord(passWord);
        //不存在则插入用户
        if (userMapper.addUserInfo(userInfoDTO)) {
            return SUCCESS;
        } else
            return SystemConst.RequestResult.ADD_USER_ERROR;
    }

    public SystemConst.RequestResult updateUserInfo(UserInfoDTO userInfoDTO)
    {
        if(userMapper.updateUserInfo(userInfoDTO))
            return SUCCESS;
        return OPERATION_FAILED;
    }

    private String decode(String keyId, String text) {
        KeyPair keyPair = redisTemplate.opsForValue().get(keyId);
        return new String(rsaEncrypt.decode(keyPair.getPrivate(), base64URLSafe.decode(text)), SystemConst.CHARSET);
    }
}
