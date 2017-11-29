package anthony.cd.app.pdc.user.action;

import anthony.cd.app.pdc.common.util.SystemConst;
import anthony.cd.app.pdc.common.util.TokenManager;
import anthony.cd.app.pdc.user.dto.UserInfoDTO;
import anthony.cd.app.pdc.user.mapper.UserMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class UserAction {

    @Resource
    private RedisTemplate<String, byte[]> redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private UserMapper userMapper;



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

    public UserInfoDTO userLogin() {
        return null;
    }

    public SystemConst.RequestResult userRegister(UserInfoDTO userInfoDTO) {
        //判断用户是否存在
        if (userMapper.isUserExist(userInfoDTO.getUserName())) {
            //存在则跳转到错误页面
            return SystemConst.RequestResult.USER_EXISTED;
        }
        //不存在则插入用户
        if (userMapper.addUserInfo(userInfoDTO)) {

            return SystemConst.RequestResult.SUCCESS;
        } else
            return SystemConst.RequestResult.ADD_USER_ERROR;
    }

}
