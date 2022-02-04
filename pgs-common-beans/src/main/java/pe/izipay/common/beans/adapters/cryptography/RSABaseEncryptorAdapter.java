package pe.izipay.common.beans.adapters.cryptography;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

import pe.izipay.common.core.exceptions.AppRuntimeException;
import pe.izipay.common.core.helpers.TextHelper;

public abstract class RSABaseEncryptorAdapter implements EncryptorPort {
	
	protected Cipher encryptCipher;
	protected Cipher decryptCipher;	
	
	@Override
	public String encrypt(String value) {
		try {			
			byte[] encryptedMessageBytes = encryptCipher.doFinal(value.getBytes());					
			return TextHelper.toBase64Encode(encryptedMessageBytes);
		} catch (Exception ex) {
			throw new AppRuntimeException(ex);
		}
	}

	public void loadAlgoritmCipher() throws NoSuchAlgorithmException, NoSuchPaddingException {		
		encryptCipher = Cipher.getInstance(RSAKeyProvider.ALGORITHM);
		decryptCipher = Cipher.getInstance(RSAKeyProvider.ALGORITHM);		
	}
	
	@Override
	public String decrypt(String value) {
		try {
			byte[] valueArray = TextHelper.toBase64Decode(value);
			byte[] decryptedMessageBytes = decryptCipher.doFinal(valueArray);
			return new String(decryptedMessageBytes, StandardCharsets.UTF_8);
		} catch (Exception ex) {
			throw new AppRuntimeException(ex);
		}
	}		
}