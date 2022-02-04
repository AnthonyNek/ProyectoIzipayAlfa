package pe.izipay.common.beans.adapters.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import lombok.Setter;

@Setter
public abstract class BaseJwtAdapter implements JwtPort {

	private static final long serialVersionUID = -2550185165626007488L;
	private static final Map<String, Object> EMPTY_CLAIMS = new HashMap<>();

	protected transient JwtParser parser;
	protected int duration;
	
	public Claims getAllClaimsFromToken(String token) {
		return parser.parseClaimsJws(token).getBody();
	}
	
	public Claims getAllClaimsFromTokenSafe(String token) {		
		try {
            return getAllClaimsFromToken(token);
		} catch (Exception ex) {
			return null;   
        }		
	}
	
	public Claims getAllClaimsFromTokenIgnoreExpiration(String token) {
		try {
            return getAllClaimsFromToken(token);
        } catch (ExpiredJwtException ex) {        	
            return ex.getClaims();            
        } catch (Exception ex) {
            return null;
        }
	}		
	
	public String getIdentifierFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}
	
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromTokenIgnoreExpiration(token);
		return claimsResolver.apply(claims);
	}	
	
	public SubjectExpiration getSubjectExpiration(String token) {
		var claims = getAllClaimsFromTokenIgnoreExpiration(token);
		if(claims != null) {
			var result = new SubjectExpiration();
			result.setSubject(claims.getSubject());
			result.setExpiration(claims.getExpiration());
			return result;
		}
		return null;
	}
	
	public boolean isTokenSigned(String token) {
		return getAllClaimsFromTokenIgnoreExpiration(token) != null;
	}
	
	public Date generateDurationToken(int duration) {
		return new Date(System.currentTimeMillis() + duration * 1000);
	}
	
	public Date generateDurationToken() {
		return generateDurationToken(duration);
	}
	
	public String generateToken(Map<String, Object> claims, String subject, int duration) {
		return generateToken(claims, subject, generateDurationToken(duration));
	}
	
	public String generateToken(Map<String, Object> claims, String subject) {
		return generateToken(claims, subject, duration);
	}
	
	public String generateToken(String subject, Date duration) {
		return generateToken(EMPTY_CLAIMS, subject, duration);
	}
	
	public String generateToken(String subject, int duration) {
		return generateToken(EMPTY_CLAIMS, subject, generateDurationToken(duration));
	}
	
	public String generateToken(String subject) {
		return generateToken(EMPTY_CLAIMS, subject);
	}				
	
	public boolean validateToken(String token) {		
        return getAllClaimsFromTokenSafe(token) != null;
	}
}
