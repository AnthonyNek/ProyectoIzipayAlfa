package pe.izipay.common.beans;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BeansDefault {
	public static final String BCRYPT = "bcrypt";
	public static final String BCRYPT_SECRET_KEY = "bcrypt_secret_key";
	public static final String RSA_KEY_PROVIDER = "rsa_key_provider";
	public static final String RSA_PRIVATE = "rsa_private";
	public static final String RSA_PUBLIC = "rsa_public";
	public static final String JWT_ACCESS = "jwt_access";
	public static final String JWT_REFRESH = "jwt_refresh";
}
