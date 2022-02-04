package pe.izipay.common.beans.adapters.cryptography;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import pe.izipay.common.core.exceptions.AppRuntimeException;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootConfiguration
@SpringBootTest
class RSAEncryptorAdapterTests {

	private static RSAPrivateEncryptorAdapter rsaPrivateEncryptor;
	private static RSAPublicEncryptorAdapter rsaPublicEncryptor;			
	
	@BeforeAll
	static void initialize() {
		AppRuntimeException.setSeparator("\n");
		
		var provider = new RSAKeyProvider();				
		provider.setPathPrivateKey("./others/rsa/oauth2.0/private.der");
		provider.setPathPublicKey("./others/rsa/oauth2.0/public.pem");
		provider.loadKeys();
		
		rsaPrivateEncryptor = new RSAPrivateEncryptorAdapter(provider.getPrivateKey());		
		rsaPublicEncryptor = new RSAPublicEncryptorAdapter(provider.getPublicKey());										
	}
		
	@Test
	void rsaPrivate() {
		String text = "Hello World...!!!";
		log.info("Entrada -> Texto Plano: {}", text);
		
		String textPrivateEncrypted = rsaPrivateEncryptor.encrypt(text);		
		log.info("RSA Privado Salida -> Texto Cifrado Base64: {}", textPrivateEncrypted);	
		
		String textPublicDecrypted = rsaPublicEncryptor.decrypt(textPrivateEncrypted);		
		log.info("RSA Public Salida -> Texto Plano: {}", textPublicDecrypted);
		
		assertThat(textPublicDecrypted).isEqualTo(text);
	}
	
	@Test
	void rsaPublic() {
		String text = "Hello World...!!!";
		log.info("Entrada -> Texto Plano: {}", text);
		
		String textPublicEncrypted = rsaPublicEncryptor.encrypt(text);		
		log.info("RSA Publico Salida -> Texto Cifrado Base64: {}", textPublicEncrypted);
		
		//no se puede desencriptar con la clave privada
		String textPrivateDecrypted = rsaPrivateEncryptor.decrypt(textPublicEncrypted);
		log.info("RSA Privado Salida -> Texto Plano: {}", textPrivateDecrypted);		
		
		assertThat(textPrivateDecrypted).isEqualTo(text);
	}

	@Test
	void rsaPrivateDecryptWithJSEncryptIntegration() {
		String textExpected = "JSEncrypt - Libreria RSA Javascript : Test de (Encriptación-Pública) -> (Desencriptación-Privada)";

		String textPublicEncrypted = "LtR2jbtwVTjiu2AQhCSnL8O43w1147GRnkRSerVE7qWQkCrpsO9zGJ4lugfPESw+u7Prj+tXQepGuD3ppZa1xAbfgENka6YAHyUS5+yzOGjseMy9NtSe0aR/GHoBnYo1gJSBGYt9x+nEj2RUrV2D6S4k++072yL9wOD5PFi0ukE/5CUSWk4B+JK5mt/tTpT08nR3YqYO44qNd+UnKOUtc57ubhLc88QLdcK1NK9c1HETO+JCEh4r8QEZIHXm0dTDHhxSeuI8U+rVX4L+FBOy3kYQ1ARM+hqr2TtUamNc0PzWiTUbSCmSXU3fdn68tfqrPla2zDgo/mPlUcZel4vT2Q==";
		log.info("RSA Publico Entrada -> Texto Cifrado Base64: {}", textPublicEncrypted);

		//no se puede desencriptar con la clave privada
		String textPrivateDecrypted = rsaPrivateEncryptor.decrypt(textPublicEncrypted);

		log.info("RSA Salida Esperada -> Texto Plano: {}", textExpected);
		log.info("RSA Salida Privado  -> Texto Plano: {}", textPrivateDecrypted);

		assertThat(textPrivateDecrypted).isEqualTo(textExpected);
	}
}
