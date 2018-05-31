package io.github.cd871127.pdc.common.util.encrypt;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;


@Component
public class Base64URLSafe {
    public String encodeToString(byte[] bytes) {
        return Base64.encodeBase64URLSafeString(bytes);
    }

    public byte[] encodeToBytes(byte[] bytes) {
        return Base64.encodeBase64URLSafe(bytes);
    }

    public byte[] decode(String str) {
        return Base64.decodeBase64(str);
    }

    public byte[] decode(byte[] bytes) {
        return Base64.decodeBase64(bytes);
    }

}
