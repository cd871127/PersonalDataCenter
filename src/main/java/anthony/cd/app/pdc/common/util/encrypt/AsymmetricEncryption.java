package anthony.cd.app.pdc.common.util.encrypt;

import java.security.KeyPair;

public interface AsymmetricEncryption extends Encryption {
    KeyPair getKeyPair();
}
