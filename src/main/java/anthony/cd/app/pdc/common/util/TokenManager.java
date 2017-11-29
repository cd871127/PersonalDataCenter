package anthony.cd.app.pdc.common.util;

import org.apache.commons.codec.binary.Base64;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    public String generateTokenByTimeAndUserName(String userName) {
        String token = String.valueOf(System.currentTimeMillis()) + userName;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(token.getBytes(CHARSET));
            Base64 base64 = new Base64();
            bytes = base64.encode(bytes);
            token = new String(bytes, CHARSET);
            stringRedisTemplate.opsForValue().set("TOKEN."+token,userName,30, TimeUnit.MINUTES);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return token;
    }
}
