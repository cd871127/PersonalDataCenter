package io.github.cd871127.pdc.common.util.encrypt;

import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;

@Component
public class RSAEncrypt implements AsymmetricEncryption {

    private final int KEY_SIZE = 2048;
    private final String KEY_ALGORITHM = "RSA";
    private final String CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding";
    private final String SIGN_ALGORITHMS = "SHA1WithRSA";

    @Override
    public byte[] encode(PublicKey publicKey, byte[] text) {
        return process(Cipher.ENCRYPT_MODE, publicKey, text);
    }

    @Override
    public byte[] decode(PrivateKey privateKey, byte[] encodedText) {
        return process(Cipher.DECRYPT_MODE, privateKey, encodedText);
    }

    private byte[] process(int mode, Key key, byte[] bytes) {
        byte[] result = null;
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(mode, key);
            result = cipher.doFinal(bytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public KeyPair getKeyPair() {
        KeyPair keyPair = null;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
            keyPairGenerator.initialize(KEY_SIZE);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return keyPair;
    }
}
