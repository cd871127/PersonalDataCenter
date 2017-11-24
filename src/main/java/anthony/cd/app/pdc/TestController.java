package anthony.cd.app.pdc;

import anthony.cd.app.pdc.common.util.Serialization;
import anthony.cd.app.pdc.common.util.SystemConst;
import anthony.cd.app.pdc.common.util.encrypt.RSAEncrypt;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
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
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate<String,byte[]> redisTemplate;

    @Resource
    private Serialization serialization;

    private byte[] bytes = null;

    @RequestMapping(value = "publickey", method = GET)
    byte[] getPublicKey() {
//        System.out.println("p");
        if (keyPair == null)
            keyPair = rsaEncrypt.getKeyPair();

        return keyPair.getPublic().getEncoded();
    }

    @RequestMapping(value = "redis/{tobeencode}", method = GET)
    void redis(@PathVariable String tobeencode) {
        bytes = tobeencode.getBytes(SystemConst.CHARSET);
        keyPair = rsaEncrypt.getKeyPair();
        bytes = rsaEncrypt.encode(keyPair.getPublic(), bytes);

        redisTemplate.opsForValue().set("test", serialization.serialize(keyPair));
//        stringRedisTemplate.opsForValue().set("test", s);
    }

    @RequestMapping(value = "redis1", method = GET)
    String redis1() {
        byte[] a = redisTemplate.opsForValue().get("test");
        printBytes(a);
        keyPair = (KeyPair) serialization.unSerialize(a);
        return new String(rsaEncrypt.decode(keyPair.getPrivate(), bytes), SystemConst.CHARSET);
    }

    @RequestMapping(value = "redis3", method = GET)
    void redis3() {
        keyPair = rsaEncrypt.getKeyPair();
        printBytes(keyPair.getPublic().getEncoded());
        byte[] a = serialization.serialize(keyPair);


        KeyPair keyPair1 = (KeyPair) serialization.unSerialize(a);

        printBytes(keyPair1.getPublic().getEncoded());

        printBytes(keyPair.getPrivate().getEncoded());
        printBytes(keyPair1.getPrivate().getEncoded());

    }

    private void printBytes(byte[] bytes) {
        for (byte b : bytes)
            System.out.print(b);
        System.out.println("");
    }
}
