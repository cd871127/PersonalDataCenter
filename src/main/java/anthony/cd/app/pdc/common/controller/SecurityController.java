package anthony.cd.app.pdc.common.controller;

import anthony.cd.app.pdc.common.util.ServerResponse;
import anthony.cd.app.pdc.common.util.SystemConst;
import anthony.cd.app.pdc.common.util.encrypt.RSAEncrypt;
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
public class SecurityController extends AbstractController {

    @Resource
    private RSAEncrypt rsaEncrypt;

    @Resource
    private RedisTemplate<String, KeyPair> redisTemplate;


    /**
     * @return 返回公钥和keyId
     */
    @RequestMapping(value = "rsaPublicKey", method = GET)
    @CrossOrigin(origins = "http://localhost:3000", methods = {GET})
    ServerResponse<Map<String, String>> initRSAPublicKey() {
        ServerResponse<Map<String, String>> serverResponse=new ServerResponse<>();
        String keyId = UUID.randomUUID().toString();
        KeyPair keyPair = rsaEncrypt.getKeyPair();
        redisTemplate.opsForValue().set(keyId, keyPair, 5, TimeUnit.SECONDS);
        Map<String, String> resMap = new HashMap<>();
        resMap.put("keyId", keyId);
        Base64 base64=new Base64();
        resMap.put("publicKey", base64.encodeToString(keyPair.getPublic().getEncoded()));
        serverResponse.setResult(SystemConst.RequestResult.SUCCESS);
        serverResponse.setData(resMap);
        return serverResponse;
    }

}
