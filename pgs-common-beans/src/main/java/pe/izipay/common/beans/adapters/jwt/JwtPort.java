package pe.izipay.common.beans.adapters.jwt;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public interface JwtPort extends Serializable {

	Map<String, Object> getAllClaimsFromToken(String token);
	Map<String, Object> getAllClaimsFromTokenSafe(String token);
	Map<String, Object> getAllClaimsFromTokenIgnoreExpiration(String token);
	String getIdentifierFromToken(String token);
	Date getExpirationDateFromToken(String token);
	SubjectExpiration getSubjectExpiration(String token);
	boolean isTokenSigned(String token);
	Date generateDurationToken(int duration);
	Date generateDurationToken();
	String generateToken(Map<String, Object> claims, String subject, Date duration);
	String generateToken(Map<String, Object> claims, String subject, int duration);
	String generateToken(Map<String, Object> claims, String subject);
	String generateToken(String subject, Date duration);	
	String generateToken(String subject, int duration);
	String generateToken(String subject);
	boolean validateToken(String token);
}
