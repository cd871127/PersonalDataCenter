package anthony.cd.app.pdc.common.util;

import org.apache.commons.codec.binary.Base64;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

@Component
public class TokenManager {

    private Charset CHARSET = Charset.forName("US-ASCII");

    private final String PREFIX = "TOKEN.";

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    public String generateTokenByTimeAndUserName(String userName) {
        String token = String.valueOf(System.currentTimeMillis()) + userName;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(token.getBytes(CHARSET));
            Base64 base64 = new Base64();
            token = base64.encodeToString(bytes);
            stringRedisTemplate.opsForValue().set(PREFIX + token, userName, 30, TimeUnit.MINUTES);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return token;
    }

    public boolean resetTokenExpire(String token) {
        return stringRedisTemplate.expire(PREFIX + token, 30, TimeUnit.MINUTES);
    }
}
