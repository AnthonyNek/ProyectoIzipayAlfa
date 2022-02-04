package pe.izipay.common.beans.adapters.cryptography;

public class SecretKeyBcrypHasherAdapter extends BcrypHasherAdapter {

	private final String secretKey;
	
	public SecretKeyBcrypHasherAdapter(String secretKey) {
		super();
		 this.secretKey = secretKey;
	}

	public SecretKeyBcrypHasherAdapter(int rounds, String secretKey) {
		super(rounds);
		this.secretKey = secretKey;
	}

	@Override
	public String encrypt(String value) {
		return super.encrypt(secretKey + value);
	}

	@Override
	public boolean verifyEncrypt(String value, String encryptedValue) {		
		return super.verifyEncrypt(secretKey + value, encryptedValue);
	}
	
}
