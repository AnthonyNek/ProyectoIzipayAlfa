package pe.izipay.common.beans.adapters.jwt;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootConfiguration
@SpringBootTest
class JwtAdapterTests {	
	
	private static SecretKeyJwtAdapter adapter;
	
	@BeforeAll
	static void initialize() {
		adapter = new SecretKeyJwtAdapter();
		adapter.setSecret("secreto");
	}
		
	@Test
	void test() {
		String identifier = "subject";
		
		adapter.setDuration(-60 * 60);
		String tokenExpired = adapter.generateToken(identifier);
		log.info("Token Expired: {}", tokenExpired);
		
		adapter.setDuration(60 * 60 * 24);
		String tokenOk = adapter.generateToken(identifier);
		log.info("Token Ok: {}", tokenOk);		
		
		assertThat(adapter.isTokenSigned("token")).isFalse();
		assertThat(adapter.isTokenSigned(null)).isFalse();
		assertThat(adapter.isTokenSigned("")).isFalse();		
		assertThat(adapter.isTokenSigned(tokenOk)).isTrue();
		assertThat(adapter.isTokenSigned(tokenExpired)).isTrue();
		
		Exception exception = assertThrows(MalformedJwtException.class, () -> adapter.getAllClaimsFromToken("token"));
		assertThat(exception).isNotNull();
		
		exception = assertThrows(IllegalArgumentException.class, () -> adapter.getAllClaimsFromToken(null));
		assertThat(exception).isNotNull();
		
		exception = assertThrows(IllegalArgumentException.class, () -> adapter.getAllClaimsFromToken(""));
		assertThat(exception).isNotNull();
		
		exception = assertThrows(ExpiredJwtException.class, () -> adapter.getAllClaimsFromToken(tokenExpired));
		assertThat(exception).isNotNull();
		
		adapter.setSecret("secreto1");
		exception = assertThrows(SignatureException.class, () -> adapter.getAllClaimsFromToken(tokenOk));
		assertThat(exception).isNotNull();
		
		exception = assertThrows(Exception.class, () -> adapter.getAllClaimsFromToken(tokenExpired));
		log.info(exception.toString());
		assertThat(exception).isNotNull();		
		
		assertThat(adapter.isTokenSigned("token")).isFalse();
		assertThat(adapter.isTokenSigned(null)).isFalse();
		assertThat(adapter.isTokenSigned("")).isFalse();
		assertThat(adapter.isTokenSigned(tokenOk)).isFalse();
		assertThat(adapter.isTokenSigned(tokenExpired)).isFalse();
		
		adapter.setDuration(15552000);
		log.info("Expiracion en 6 Meses: {}", adapter.generateToken(identifier));
		adapter.setDuration(10368000);
		log.info("Expiracion en 4 Meses: {}", adapter.generateToken(identifier));
		adapter.setDuration(7776000);
		log.info("Expiracion en 3 Meses: {}", adapter.generateToken(identifier));
		adapter.setDuration(2628000);
		log.info("Expiracion en 1 Mes: {}", adapter.generateToken(identifier));
		adapter.setDuration(2592000);
		log.info("Expiracion en 3 Semanas: {}", adapter.generateToken(identifier));
		adapter.setDuration(1209600);
		log.info("Expiracion en 2 Semanas: {}", adapter.generateToken(identifier));
		adapter.setDuration(604800);
		log.info("Expiracion en 1 Semana: {}", adapter.generateToken(identifier));
	}	

}
