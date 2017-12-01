package anthony.cd.app.pdc.common.util;

import anthony.cd.app.pdc.common.util.encrypt.Base64URLSafe;
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
    @Resource
    private Base64URLSafe base64URLSafe;


    public String generateTokenByTimeAndUserName(String userName) {
        String token = String.valueOf(System.currentTimeMillis()) + userName;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(token.getBytes(CHARSET));
            token = base64URLSafe.encodeToString(bytes);
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
