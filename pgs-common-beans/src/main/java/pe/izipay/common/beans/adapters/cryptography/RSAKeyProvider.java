package pe.izipay.common.beans.adapters.cryptography;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.springframework.core.env.Environment;

import lombok.Getter;
import pe.izipay.common.core.exceptions.AppRuntimeException;
import pe.izipay.common.core.helpers.FileHelper;

@Getter
public class RSAKeyProvider {				
		
	public static final String ALGORITHM = "RSA";
	
	protected String pathPrivateKey;
	protected String pathPublicKey;
	
	protected Environment environment;
	
	protected RSAPrivateKey privateKey;
	protected RSAPublicKey publicKey;
	
	public static RSAPrivateKey readPrivateKey(File file) {		
		try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {	
			byte[] keyBytes = new byte[(int) file.length()];
			dis.readFully(keyBytes);			

			PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
			return (RSAPrivateKey) keyFactory.generatePrivate(spec);	
		} catch (Exception ex) {
			throw new AppRuntimeException(ex);
		}
	}
	
	public static RSAPrivateKey readPrivateKey(String file) {
		return readPrivateKey(new File(file));
	}
	
	public static RSAPublicKey readPublicKey(File file) {
		try {
			PemFile pemFile = new PemFile(file.getAbsolutePath());
			byte[] encoded = pemFile.getPemObject().getContent();
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encoded);
			KeyFactory kf = KeyFactory.getInstance(ALGORITHM);
			return (RSAPublicKey) kf.generatePublic(keySpec);
		} catch (Exception ex) {
			throw new AppRuntimeException(ex);
		}
	}
	
	public static RSAPublicKey readPublicKey(String file) {
		return readPublicKey(new File(file));
	}
	
	public void setPathPublicKey(String pathPublicKey) {
		this.pathPublicKey = pathPublicKey;
	}
	
	public void setPathPrivateKey(String pathPrivateKey) {
		this.pathPrivateKey= pathPrivateKey;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
	
	public void loadPrivateKey() {		
		var path = FileHelper.formatPathEnviromentString(pathPrivateKey, environment);
		privateKey = FileHelper.isFileResource(path) ? readPrivateKey(FileHelper.loadFileResource(path)) : readPrivateKey(path);			
	}
	
	public void loadPublicKey() {
		var path = FileHelper.formatPathEnviromentString(pathPublicKey, environment);
		publicKey =  FileHelper.isFileResource(path) ? readPublicKey(FileHelper.loadFileResource(path)) : readPublicKey(path);		
	}
	
	public void loadKeys() {		
		loadPrivateKey();
		loadPublicKey();		
	}
}