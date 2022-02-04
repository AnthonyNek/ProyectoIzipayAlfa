package pe.izipay.common.beans.adapters.cryptography;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcrypHasherAdapter implements HasherPort {
			
	private final BCryptPasswordEncoder passwordEncoder;
	
	public BcrypHasherAdapter(int rounds) {
		this.passwordEncoder = new BCryptPasswordEncoder(rounds);		
	}
	
	public BcrypHasherAdapter() {
		this(10);
	}
	
	public String encrypt(String value) {
		return passwordEncoder.encode(value);
	}
	
	public boolean verifyEncrypt(String value, String encryptedValue) {
		return passwordEncoder.matches(value, encryptedValue);
	}
}
