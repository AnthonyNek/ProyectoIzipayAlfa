package pe.izypay.pgs.mstemplate.infrastructure.middlewares;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import pe.izipay.common.beans.BeansDefault;
import pe.izipay.common.beans.adapters.cryptography.BcrypHasherAdapter;
import pe.izipay.common.beans.adapters.cryptography.RSAKeyProvider;
import pe.izipay.common.beans.adapters.cryptography.RSAPrivateEncryptorAdapter;
import pe.izipay.common.beans.adapters.cryptography.SecretKeyBcrypHasherAdapter;

@Configuration
public class CryptographyMiddleware {
	
	private final RSAKeyProvider rsaKeyProvider;
	private SecretKeyBcrypHasherAdapter secretKeyBcrypHasherAdapter;
	
	public CryptographyMiddleware(Environment environment) {
		rsaKeyProvider = new RSAKeyProvider();
		rsaKeyProvider.setEnvironment(environment);
	}
	
	@Value("${app.security.encryption.rsa.private}")
	public void setPrivateKeyPath(String privateKeyPath) {
		rsaKeyProvider.setPathPrivateKey(privateKeyPath); 
	}
	
	@Value("${app.security.encryption.secret-key}")
	public void setHashSecretKey(String hashSecretKey) {
		secretKeyBcrypHasherAdapter = new SecretKeyBcrypHasherAdapter(hashSecretKey);
	}
	
	@PostConstruct
	public void initialize() {		
		rsaKeyProvider.loadPrivateKey();
	}
	
	@Primary
	@Bean(name = BeansDefault.BCRYPT)
	public BcrypHasherAdapter beanBcrypHasherAdapter() {
		return new BcrypHasherAdapter();
	}
	
	@Bean(name = BeansDefault.BCRYPT_SECRET_KEY)	
	public SecretKeyBcrypHasherAdapter beanSecretKeyBcrypHasherAdapter() {
		return secretKeyBcrypHasherAdapter;
	}		
	
	@Bean(name = BeansDefault.RSA_KEY_PROVIDER)
	public RSAKeyProvider beanRSAKeyProvider() {				
		return rsaKeyProvider;
	}
	
	@Bean(name = BeansDefault.RSA_PRIVATE)
	public RSAPrivateEncryptorAdapter beanRSAPrivateEncryptorAdapter() {
		return new RSAPrivateEncryptorAdapter(rsaKeyProvider.getPrivateKey());
	}
}