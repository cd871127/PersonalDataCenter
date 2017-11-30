package anthony.cd.app.pdc.user.action;

import anthony.cd.app.pdc.common.util.SystemConst;
import anthony.cd.app.pdc.common.util.encrypt.RSAEncrypt;
import anthony.cd.app.pdc.user.dto.UserInfoDTO;
import anthony.cd.app.pdc.user.mapper.UserMapper;
import org.apache.commons.codec.binary.Base64;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.KeyPair;
import java.util.Map;

@Service
public class UserAction {

    @Resource
    private UserMapper userMapper;
    @Resource
    private RSAEncrypt rsaEncrypt;
    @Resource
    private RedisTemplate<String, KeyPair> redisTemplate;

    /**
     * 生成token
     *
     * @return 返回token
     */
    private String generateToken(String userName) {


        return null;
    }


    public UserInfoDTO getUserInfoDTOByUserName(Map<String, String> paraMap) {


        return null;
    }

    public UserInfoDTO userLogin(Map<String, String> paraMap) {
        String passWord = paraMap.get("passWord");
        passWord = decode(paraMap.get("keyId"), passWord);
        paraMap.put("passWord", passWord);
        return userMapper.getUserInfo(paraMap);
    }

    public SystemConst.RequestResult userRegister(UserInfoDTO userInfoDTO, String keyId) {
        //判断用户是否存在
        if (userMapper.isUserExist(userInfoDTO.getUserName())) {
            //存在则跳转到错误页面
            return SystemConst.RequestResult.USER_EXISTED;
        }
        //不存在则插入用户
        //TODO 解密密码
        String passWord = decode(keyId, userInfoDTO.getPassWord());
        userInfoDTO.setPassWord(passWord);
        if (userMapper.addUserInfo(userInfoDTO)) {
            return SystemConst.RequestResult.SUCCESS;
        } else
            return SystemConst.RequestResult.ADD_USER_ERROR;
    }

    private String decode(String keyId, String text) {
        KeyPair keyPair = redisTemplate.opsForValue().get(keyId);
        Base64 base64 = new Base64();
        return new String(rsaEncrypt.decode(keyPair.getPrivate(), base64.decode(text)), SystemConst.CHARSET);
    }
}
