package pe.izipay.common.beans.adapters.cryptography;

public interface EncryptorPort {
	String encrypt(String value);	
	String decrypt(String value);
}
