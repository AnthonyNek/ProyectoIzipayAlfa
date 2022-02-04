package pe.izipay.common.beans.adapters.cryptography;

import java.security.PrivateKey;

import javax.crypto.Cipher;

import pe.izipay.common.core.exceptions.AppRuntimeException;

public class RSAPrivateEncryptorAdapter extends RSABaseEncryptorAdapter {		
	
	public RSAPrivateEncryptorAdapter(PrivateKey privateKey) {
		try {
			loadAlgoritmCipher();
			super.encryptCipher.init(Cipher.ENCRYPT_MODE, privateKey);			
			super.decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
		} catch (Exception ex) {
			throw new AppRuntimeException(ex);
		}				
	}
}
