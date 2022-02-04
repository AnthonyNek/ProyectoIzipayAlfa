package pe.izypay.pgs.mstemplate.infrastructure.middlewares;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pe.izipay.common.beans.BeansDefault;
import pe.izipay.common.beans.adapters.jwt.JwtPort;
import pe.izipay.common.beans.middlewares.security.AuthHeaderFilter;
import pe.izipay.common.beans.middlewares.security.BaseWebSecurityMiddeware;
import pe.izipay.common.beans.middlewares.security.IDefaultCorsConfig;

import java.util.HashMap;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityMiddleware extends BaseWebSecurityMiddeware implements IDefaultCorsConfig {
	
	public SecurityMiddleware(@Qualifier(BeansDefault.JWT_ACCESS) JwtPort jwtPortAccess) {
		super(jwtPortAccess);
	}

	@Value("${springdoc.api-docs.path}")
	public void setSwaggerUiPath(String swaggerUiPath) {
		super.swaggerUiPath = swaggerUiPath;
	}
	
	@Value("${springdoc.swagger-ui.path}")
	public void setOpenApiPath(String openApiPath) {
		super.openApiPath = openApiPath;
	}
	
	@Value("${app.security.enable}")
	public void setAppSecurityEnable(boolean appSecurityEnable) {
		super.appSecurityEnable = appSecurityEnable;
	}
	
	@Override
	public AuthHeaderFilter createAuthHeaderFilter(JwtPort jwtAdapter, boolean appSecurityEnable) {
		var claims = new HashMap<String, Object>();
		claims.put("sub", "usuario_id");
        
		return new AuthHeaderFilter(jwtAdapter, appSecurityEnable, claims);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return createWebMvcConfigurer();
	}
}