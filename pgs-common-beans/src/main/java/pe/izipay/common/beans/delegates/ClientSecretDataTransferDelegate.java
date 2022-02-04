package pe.izipay.common.beans.delegates;

import lombok.Setter;
import pe.izipay.common.beans.adapters.cryptography.EncryptorPort;
import pe.izipay.common.core.exceptions.AppModuleException;
import pe.izipay.common.core.interfaces.errors.IReadOnlyError;

import java.util.function.Function;

@Setter
public class ClientSecretDataTransferDelegate {

    private EncryptorPort encryptorPort;
    private boolean enabled;
    private IReadOnlyError<?> errorRead;

    private Function<String, String> hide;
    private Function<String, String> show;

    public void reload() {
        if(enabled) {
            hide = encryptorPort::encrypt;
            show = encryptorPort::decrypt;
        } else {
            hide = show = x -> x;
        }
    }

    public void reload(boolean enabled) {
        this.enabled = enabled;
        reload();
    }

    public String hide(String value) {
        try {
            return hide.apply(value);
        } catch (Exception ex){
            throw new AppModuleException(errorRead);
        }
    }

    public String show(String value) {
        try {
            return show.apply(value);
        } catch (Exception ex){
            throw new AppModuleException(ex, errorRead);
        }
    }
}
