package pe.izipay.common.beans.middlewares.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import pe.izipay.common.beans.adapters.jwt.JwtPort;
import pe.izipay.common.beans.restful.RestCrudConstant;

//import lombok.extern.slf4j.Slf4j;

//@Slf4j
public class AuthHeaderFilter extends OncePerRequestFilter {    
    
    private final JwtPort jwtAdapter;
    private final AuthHeaderAction authHeaderAction;
    private final Map<String, Object> mockClaims;    
    
    private static Map<String, Object> buildClaims(String claim, Object value) {
    	var map = new HashMap<String, Object>();
    	map.put(claim, value);
		return map;
    }
    
    public AuthHeaderFilter(JwtPort jwtAdapter, boolean appSecurityEnable, Map<String, Object> mockClaims) {
        this.jwtAdapter = jwtAdapter;
        this.mockClaims = mockClaims;
        this.authHeaderAction = appSecurityEnable ? this::doFilterInternalSecured : this::doFilterInternalUnsecured;        
    }
    
    public AuthHeaderFilter(JwtPort jwtAdapter, boolean appSecurityEnable) {
    	this(jwtAdapter, appSecurityEnable, new HashMap<>());
    }
    
    public AuthHeaderFilter(JwtPort jwtAdapter, boolean appSecurityEnable, String claim, Object value) {
    	this(jwtAdapter, appSecurityEnable, AuthHeaderFilter.buildClaims(claim, value));
    }
    
    public AuthHeaderFilter(JwtPort jwtAdapter, boolean appSecurityEnable, MockClaimsAction mockClaimsAction) {
    	this(jwtAdapter, appSecurityEnable, mockClaimsAction.action());
    }

    private void doFilterInternalSecured(HttpServletRequest request, HttpServletResponse response) {
        if(request.getServletPath().startsWith(RestCrudConstant.SCOPE_PUBLIC)) return;

        String authorizationHeader = request.getHeader(RestCrudConstant.HEADER_AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith(RestCrudConstant.HEADER_TOKEN_PREFIX)) {
            var token = authorizationHeader.substring(RestCrudConstant.HEADER_TOKEN_PREFIX.length());
            var claims = jwtAdapter.getAllClaimsFromTokenSafe(token);
            if (claims != null) {                
                var authentication = new UsernamePasswordAuthenticationToken(claims, null, null);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
    }       

    private void doFilterInternalUnsecured(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextHolder.getContext()
                .setAuthentication(new UsernamePasswordAuthenticationToken(mockClaims, null, null));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

    	//log.trace(request.getServletPath());
        //CollectionHelper.forEach(request.getHeaderNames(), key -> log.trace("Header[{}] : {}", key, request.getHeader(key)));
    	authHeaderAction.action(request, response);
    	filterChain.doFilter(request, response);
    }
}