package anthony.cd.app.pdc.common.controller;

import anthony.cd.app.pdc.common.util.encrypt.RSAEncrypt;
import anthony.cd.app.pdc.common.util.redis.KeyPairSerializer;
import org.apache.commons.codec.binary.Base64;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController

@RequestMapping("/security")
public class SecurityController extends AbstractController  {

    @Resource
    private RSAEncrypt rsaEncrypt;

    @Resource
    private RedisTemplate<String, byte[]> redisTemplate;

    @Resource
    private KeyPairSerializer keyPairSerializer;


    /**
     * @return 返回公钥和keyId
     */
    @RequestMapping(value = "rsaPublicKey", method = GET)
    @CrossOrigin(origins = "http://localhost:3000", methods = {GET})
    Map<String, String> initRSAPublicKey() {
        String keyId = UUID.randomUUID().toString();
        KeyPair keyPair = rsaEncrypt.getKeyPair();
        redisTemplate.opsForValue().set(keyId, keyPairSerializer.serialize(keyPair), 5, TimeUnit.SECONDS);
        Map<String, String> resMap = new HashMap<>();
        resMap.put("keyId", keyId);
        Base64 base64 = new Base64();
        resMap.put("publicKey", base64.encodeAsString(keyPair.getPublic().getEncoded()));
        return resMap;
    }

    /**
     * @return 返回公钥和keyId
     */
//    @RequestMapping(value = "rsaPublicKey/{keyId}", method = GET)
//    Map<String, String> getRSAPublicKey(@PathVariable String keyId) {
//        KeyPair keyPair = keyPairMap.get(keyId);
//        if (null == keyPair) {
//            //TODO 没有找到keyPair需要重新请求initRSAPublicKey
//            return null;
//        }
//        Map<String, String> resMap = new HashMap<>();
//        resMap.put("keyId", keyId);
//        Base64 base64 = new Base64();
//        resMap.put("publicKey", base64.encodeAsString(keyPair.getPublic().getEncoded()));
//        return resMap;
//    }


}
