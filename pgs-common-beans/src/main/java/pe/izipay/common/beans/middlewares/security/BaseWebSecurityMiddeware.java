package pe.izipay.common.beans.middlewares.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;
import pe.izipay.common.beans.adapters.jwt.JwtPort;
import pe.izipay.common.beans.restful.RestCrudConstant;

@RequiredArgsConstructor
public abstract class BaseWebSecurityMiddeware extends WebSecurityConfigurerAdapter {

	protected final JwtPort jwtAdapter;
	protected final JwtAuthEntryPoint jwtAuthEntryPoint = new JwtAuthEntryPoint();
		
	protected String swaggerUiPath;		
	protected String openApiPath;		
	protected boolean appSecurityEnable;

	public abstract AuthHeaderFilter createAuthHeaderFilter(JwtPort jwtAdapter, boolean appSecurityEnable);
	
	@Bean
	public AuthHeaderFilter authenticationJwtTokenFilter() {
		return createAuthHeaderFilter(jwtAdapter, appSecurityEnable);
	}

	@Override
	public void configure(WebSecurity webSecurity) {
		webSecurity.ignoring().antMatchers(
				swaggerUiPath,
                swaggerUiPath + "/**",
                openApiPath,
				openApiPath + "/**",				
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",                                
                "/webjars/**");
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		if(appSecurityEnable) {
			httpSecurity.cors().and().csrf().disable()
					.exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint)
					.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
					.and().authorizeRequests()
						.antMatchers(RestCrudConstant.SCOPE_PUBLIC + "/**").permitAll()
						.antMatchers(RestCrudConstant.PATH_ERROR_TYPE).permitAll()
						.antMatchers(RestCrudConstant.PATH_ERROR_TYPE_DEFINITION).permitAll()
					.anyRequest().authenticated();

			httpSecurity.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		} else {
			httpSecurity.csrf().disable()				
			.authorizeRequests().antMatchers("/**")
			.permitAll();
		}
	}
}