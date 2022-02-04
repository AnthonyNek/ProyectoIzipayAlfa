package pe.izipay.common.beans.adapters.cryptography;

import java.security.PublicKey;

import javax.crypto.Cipher;

import pe.izipay.common.core.exceptions.AppRuntimeException;

public class RSAPublicEncryptorAdapter extends RSABaseEncryptorAdapter {				
	
	public RSAPublicEncryptorAdapter(PublicKey publicKey) {
		try {
			loadAlgoritmCipher();
			super.encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);			
			super.decryptCipher.init(Cipher.DECRYPT_MODE, publicKey);
		} catch (Exception ex) {
			throw new AppRuntimeException(ex);
		}
	}	
}
