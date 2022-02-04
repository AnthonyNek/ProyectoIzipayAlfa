package pe.izipay.common.beans.adapters.jwt;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.Map;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RSAJwtAdapter extends BaseJwtAdapter {

	private static final long serialVersionUID = 2428964173393088946L;

	protected PrivateKey privateKey;	

	public void setPublicKey(PublicKey publicKey) {
		super.parser = Jwts.parser().setSigningKey(publicKey);
	}
	
	public String generateToken(Map<String, Object> claims, String subject, Date duration) {
		return Jwts.builder()				
				.setClaims(claims)
				.setSubject(subject)
				.setExpiration(duration)				
				.signWith(SignatureAlgorithm.RS256, privateKey)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.compact();
	}			
}