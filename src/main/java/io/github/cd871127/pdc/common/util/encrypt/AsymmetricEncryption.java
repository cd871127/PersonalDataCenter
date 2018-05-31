package io.github.cd871127.pdc.common.util.encrypt;

import java.security.KeyPair;

public interface AsymmetricEncryption extends Encryption {
    KeyPair getKeyPair();
}
