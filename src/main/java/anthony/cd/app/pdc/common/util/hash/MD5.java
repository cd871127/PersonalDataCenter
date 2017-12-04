package anthony.cd.app.pdc.common.util.hash;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public static byte[] byteToMD5Bytes(byte[] bytes) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            bytes = md.digest(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            bytes = null;
        }
        return bytes;
    }

    public static String byteToMD5String(byte[] bytes) {
        bytes = byteToMD5Bytes(bytes);
        return Base64.encodeBase64String(bytes);
    }
}
