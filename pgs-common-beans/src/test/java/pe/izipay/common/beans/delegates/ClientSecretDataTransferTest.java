package pe.izipay.common.beans.delegates;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pe.izipay.common.beans.BeansDefault;
import pe.izipay.common.beans.adapters.cryptography.EncryptorPort;

import static org.assertj.core.api.Assertions.assertThat;

class ClientSecretDataTransferTest {

    private static ClientSecretDataTransferDelegate delegate;

    @BeforeAll
    static void init() {
        delegate = new ClientSecretDataTransferDelegate();
        delegate.setEncryptorPort(Mockito.mock(EncryptorPort.class));
    }

    @Test
    void hide() {
        String value = BeansDefault.BCRYPT +
                BeansDefault.BCRYPT_SECRET_KEY +
                BeansDefault.RSA_KEY_PROVIDER +
                BeansDefault.RSA_PRIVATE +
                BeansDefault.RSA_PUBLIC +
                BeansDefault.JWT_ACCESS +
                BeansDefault.JWT_REFRESH;

        delegate.reload(true);
        assertThat(delegate.hide(value)).isNull();
        delegate.reload(false);
        assertThat(delegate.hide(value)).isNotNull();
    }

    @Test
    void show() {
        String value = "test";
        delegate.reload(true);
        assertThat(delegate.show(value)).isNull();
        delegate.reload(false);
        assertThat(delegate.show(value)).isNotNull();
    }
}