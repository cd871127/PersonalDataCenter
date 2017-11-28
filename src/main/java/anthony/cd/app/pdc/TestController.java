package anthony.cd.app.pdc;

import anthony.cd.app.pdc.common.util.SystemConst;
import anthony.cd.app.pdc.common.util.encrypt.RSAEncrypt;
import anthony.cd.app.pdc.common.util.redis.KeyPairSerializer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.KeyPair;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/test")
public class TestController {

    private KeyPair keyPair = null;

    @Resource
    private RSAEncrypt rsaEncrypt;


    @Resource
    private KeyPairSerializer keyPairSerializer;

    @Resource
    private RedisTemplate<String, KeyPair> redisTemplate;


    private byte[] bytes = null;

    @RequestMapping(value = "publickey", method = GET)
    byte[] getPublicKey() {
        if (keyPair == null)
            keyPair = rsaEncrypt.getKeyPair();

        return keyPair.getPublic().getEncoded();
    }

    @RequestMapping(value = "redis/{tobeencode}", method = GET)
    void redis(@PathVariable String tobeencode) {
        bytes = tobeencode.getBytes(SystemConst.CHARSET);
        keyPair = rsaEncrypt.getKeyPair();
        bytes = rsaEncrypt.encode(keyPair.getPublic(), bytes);

        redisTemplate.opsForValue().set("test", keyPair);
//        stringRedisTemplate.opsForValue().set("test", s);
    }

    @RequestMapping(value = "redis1", method = GET)
    String redis1() {
        keyPair = redisTemplate.opsForValue().get("test");
        return new String(rsaEncrypt.decode(keyPair.getPrivate(), bytes), SystemConst.CHARSET);
    }


    private void printBytes(byte[] bytes) {
        for (byte b : bytes)
            System.out.print(b);
        System.out.println("");
    }
}
