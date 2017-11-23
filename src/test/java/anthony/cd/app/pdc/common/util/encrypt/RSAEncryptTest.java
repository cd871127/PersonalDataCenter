package anthony.cd.app.pdc.common.util.encrypt;

import anthony.cd.app.pdc.common.util.SystemConst;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.security.KeyPair;

public class RSAEncryptTest {
    @Test
    public void test() {
        AsymmetricEncryption asymmetricEncryption = new RSAEncrypt();
        KeyPair keyPair = asymmetricEncryption.getKeyPair();

        String test = "你好";
        System.out.println(test);

        byte[] mid = asymmetricEncryption.encode(keyPair.getPublic(), test.getBytes(SystemConst.CHARSET));

        System.out.println(new String(mid,SystemConst.CHARSET));

        byte[] res = asymmetricEncryption.decode(keyPair.getPrivate(), mid);

        System.out.println(new String(res,SystemConst.CHARSET));


        System.out.println(DigestUtils.sha1Hex(test.getBytes()));

    }

}