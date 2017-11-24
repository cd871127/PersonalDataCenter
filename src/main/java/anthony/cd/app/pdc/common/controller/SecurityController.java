package anthony.cd.app.pdc.common.controller;

import anthony.cd.app.pdc.common.util.encrypt.RSAEncrypt;
import org.apache.commons.codec.binary.Base64;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/security")
public class SecurityController {

    @Resource
    private RSAEncrypt rsaEncrypt;

    private HashMap<String, KeyPair> keyPairMap = new HashMap<>();

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * @return 返回公钥和keyId
     */
    @RequestMapping(value = "rsaPublicKey", method = RequestMethod.GET)
    Map<String, String> initRSAPublicKey() {
        String keyId = UUID.randomUUID().toString();
        KeyPair keyPair = rsaEncrypt.getKeyPair();
        keyPairMap.put(keyId, keyPair);
        Map<String, String> resMap = new HashMap<>();
        resMap.put("keyId", keyId);
        Base64 base64 = new Base64();
        resMap.put("publicKey", base64.encodeAsString(keyPair.getPublic().getEncoded()));
        return resMap;
    }

    /**
     * @return 返回公钥和keyId
     */
    @RequestMapping(value = "rsaPublicKey/{keyId}", method = RequestMethod.GET)
    Map<String, String> getRSAPublicKey(@PathVariable String keyId) {
        KeyPair keyPair = keyPairMap.get(keyId);
        if (null == keyPair) {
            //TODO 没有找到keyPair需要重新请求initRSAPublicKey
            return null;
        }
        Map<String, String> resMap = new HashMap<>();
        resMap.put("keyId", keyId);
        Base64 base64 = new Base64();
        resMap.put("publicKey", base64.encodeAsString(keyPair.getPublic().getEncoded()));
        return resMap;
    }


}
