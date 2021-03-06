package io.github.cd871127.pdc.common.util.encrypt;

import java.security.PrivateKey;
import java.security.PublicKey;

public interface Encryption {
    byte[] encode(PublicKey publicKey, byte[] text);

    byte[] decode(PrivateKey privateKey, byte[] encodedText);
}
