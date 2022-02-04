package pe.izipay.common.beans.adapters.cryptography;

public interface HasherPort {
	
	String encrypt(String value);	
	boolean verifyEncrypt(String value, String encryptedValue);
}
