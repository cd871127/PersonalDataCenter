package anthony.cd.app.pdc.user.action;

import anthony.cd.app.pdc.user.dto.UserInfoDTO;
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


    /**
     * 生成token
     *
     * @return 返回token
     */
    private String generateToken(String userName) {


        return null;
    }


    public UserInfoDTO getUserInfoDTO(Map<String, String> paraMap) {
        if (paraMap.get("token") == null)
            ;


        return null;
    }

}
