package pe.izipay.common.beans.middlewares.security;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.env.Environment;
import pe.izipay.common.beans.adapters.cryptography.RSAKeyProvider;
import pe.izipay.common.beans.adapters.jwt.BaseJwtAdapter;
import pe.izipay.common.beans.adapters.jwt.JwtPort;
import pe.izipay.common.beans.adapters.jwt.RSAJwtAdapter;
import pe.izipay.common.beans.adapters.jwt.SecretKeyJwtAdapter;
import pe.izipay.common.core.exceptions.AppRuntimeException;
import pe.izipay.common.core.helpers.TextHelper;

@NoArgsConstructor
@Setter
public class JwtConfigurationBuilder {

    public static final String TYPE_RSA = "rsa";
    public static final String TYPE_SECRET_KEY = "secret-key";

    private String type;

    private int duration;
    private String pathPrivateKey;
    private String pathPublicKey;
    private Environment environment;

    private String secretKey;

    public JwtConfigurationBuilder(Environment environment) {
        this.environment = environment;
    }

    public JwtPort build() {
        BaseJwtAdapter jwtAdapter;

        type = type.toLowerCase();
        if(TYPE_RSA.equals(type)) {
            var rsaKeyProvider = new RSAKeyProvider();
            rsaKeyProvider.setEnvironment(environment);
            rsaKeyProvider.setPathPrivateKey(pathPrivateKey);
            rsaKeyProvider.setPathPublicKey(pathPublicKey);

            if(!TextHelper.isNullOrEmpty(rsaKeyProvider.getPathPrivateKey())) {
                rsaKeyProvider.loadPrivateKey();
            }
            if(!TextHelper.isNullOrEmpty(rsaKeyProvider.getPathPublicKey())) {
                rsaKeyProvider.loadPublicKey();
            }

            var rsaJwtAdapter = new RSAJwtAdapter();
            rsaJwtAdapter.setPrivateKey(rsaKeyProvider.getPrivateKey());
            rsaJwtAdapter.setPublicKey(rsaKeyProvider.getPublicKey());

            jwtAdapter = rsaJwtAdapter;
        } else if (TYPE_SECRET_KEY.equals(type)) {
            var secretKeyJwtAdapter = new SecretKeyJwtAdapter();
            secretKeyJwtAdapter.setSecret(secretKey);

            jwtAdapter = secretKeyJwtAdapter;
        } else {
            throw new AppRuntimeException("Tipo de encriptación jwt no soportado.");
        }

        jwtAdapter.setDuration(duration);
        return jwtAdapter;
    }
}
