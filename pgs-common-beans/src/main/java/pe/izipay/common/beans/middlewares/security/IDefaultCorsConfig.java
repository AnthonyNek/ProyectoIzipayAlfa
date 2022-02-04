package pe.izipay.common.beans.middlewares.security;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public interface IDefaultCorsConfig {
    
	default String getMapping() {
		return "/**";
	}
			
	default boolean getCredentials() {
		return false;
	}
	
	default String[] getAllowedMethods() {
    	return new String[] {"HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"};
    }
	
	default String[] getAllowedOrigins() {
    	return new String[] {"*"};
    }
	
	default String[] getAllowedHeaders() {
    	return new String[] {"*"};
    }
	
	default WebMvcConfigurer createWebMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping(getMapping())
                        .allowedMethods(getAllowedMethods())
                        .allowCredentials(getCredentials())
                        .allowedOrigins(getAllowedOrigins())
                        .allowedHeaders(getAllowedHeaders());
            }
        };
    }	
}
