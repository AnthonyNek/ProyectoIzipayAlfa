package pe.izipay.common.beans.adapters.jwt;

import java.util.Date;
import java.util.Map;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class SecretKeyJwtAdapter extends BaseJwtAdapter {

	private static final long serialVersionUID = -2550185165626007489L;	
	
	private String secret;
	
	public void setSecret(String secret) {
		this.secret = secret;
		super.parser = Jwts.parser().setSigningKey(secret);
	}
	
	public String generateToken(Map<String, Object> claims, String subject, Date duration) {
		return Jwts.builder()				
				.setClaims(claims)
				.setSubject(subject)
				.setExpiration(duration)				
				.signWith(SignatureAlgorithm.HS512, secret)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.compact();
	}
}
