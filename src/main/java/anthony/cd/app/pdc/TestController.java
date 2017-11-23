package anthony.cd.app.pdc;

import anthony.cd.app.pdc.common.util.encrypt.RSAEncrypt;
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

    @RequestMapping(value = "publickey", method = GET)
    byte[] getPublicKey() {
        if (keyPair == null)
            keyPair = rsaEncrypt.getKeyPair();
        return keyPair.getPublic().getEncoded();
    }

}
