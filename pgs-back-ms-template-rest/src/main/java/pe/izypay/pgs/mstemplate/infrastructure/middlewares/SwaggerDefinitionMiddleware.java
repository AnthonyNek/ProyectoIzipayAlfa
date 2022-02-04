package pe.izypay.pgs.mstemplate.infrastructure.middlewares;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import pe.izypay.pgs.mstemplate.domain.types.ErrorType;
import pe.izipay.common.beans.BeansDefault;
import pe.izipay.common.beans.adapters.json.JsonAdapter;
import pe.izipay.common.beans.adapters.jwt.JwtPort;
import pe.izipay.common.beans.middlewares.security.JwtConfigurationBuilder;
import pe.izipay.common.beans.restful.ErrorTypeController;

@OpenAPIDefinition(
		info = @Info(
				title = "PGS TEMPLATE API",
				version = "1.0.0",
				contact = @Contact(
						name = "Stefanini API Support",
						url = "https://www.bluepoint.com/es/contact",
						email = "wilb928@gmail.com"),
				license = @License(
						name = "Apache 2.0",
		                url = "http://www.apache.org/licenses/LICENSE-2.0.html"),
				description = "<h2><b>Template Information</b></h2>" 
		                + "<h3>Formatos de fecha</h3>"
		                + "<ul>"
		                + "<li>Fecha: <b>yyyy-MM-dd</b></li>"
		                + "<li>Hora: <b>HH:mm:ss</b></li>"
		                + "<li>Fecha y Hora (<a href=\"https://es.wikipedia.org/wiki/ISO_8601\" target=\"_blank\">ISO 8601</a>): <b>yyyy-MM-ddTHH:mm:ss.SSSZ</b></li>"
		                + "<ul>"
				),
		servers = {
				@Server(url="/api/pgs/v1/template")
		},		
		security = {
				@SecurityRequirement(
						name = "bearerAuth"
		        )
		},
		externalDocs = @ExternalDocumentation(
				description = "Especificación de la emplementacion de los endpoints",
				url = "https://www.google.com"
				
		)
)
@SecurityScheme(
		name = "pgs-scheme",
		type = SecuritySchemeType.HTTP,
		scheme = "bearer",
		in = SecuritySchemeIn.HEADER,
		description = "Autenticación Json Web Token (JWT)",
		bearerFormat = "JWT"
)
@Configuration
public class SwaggerDefinitionMiddleware {

	private final ObjectMapper objectMapper;

	private final JwtConfigurationBuilder jwtConfigurationBuilderAccess;
	private final JwtConfigurationBuilder jwtConfigurationBuilderRefresh;

	public SwaggerDefinitionMiddleware(ObjectMapper objectMapper, Environment environment) {
		this.objectMapper = objectMapper;

		jwtConfigurationBuilderAccess = new JwtConfigurationBuilder(environment);
		jwtConfigurationBuilderRefresh = new JwtConfigurationBuilder(environment);
	}

	@Bean
	public JsonAdapter beanJsonAdapter() {
		return new JsonAdapter(objectMapper);
	}
	
	@Bean
	public ErrorTypeController beanMicroServiceErrorTypeController() {
		return new ErrorTypeController(ErrorType::values);
	}

	@Value("${app.security.jwt.access-token.type}")
	public void setTypeJwtAccess(String type) {
		jwtConfigurationBuilderAccess.setType(type);
	}

	@Value("${app.security.jwt.access-token.duration}")
	public void setDurationJwtAccess(int duration) {
		jwtConfigurationBuilderAccess.setDuration(duration);
	}

	@Value("${app.security.jwt.access-token.rsa.private:}")
	public void setPrivateKeyPathJwtAccess(String privateKeyPath) {
		jwtConfigurationBuilderAccess.setPathPrivateKey(privateKeyPath);
	}

	@Value("${app.security.jwt.access-token.rsa.public:}")
	public void setPublicKeyPathJwtAccess(String publicKeyPath) {
		jwtConfigurationBuilderAccess.setPathPublicKey(publicKeyPath);
	}

	@Value("${app.security.jwt.access-token.secret-key:}")
	public void setSecretKeyJwtAccess(String secretKey) {
		jwtConfigurationBuilderAccess.setSecretKey(secretKey);
	}

	@Value("${app.security.jwt.refresh-token.type}")
	public void setTypeJwtRefresh(String type) {
		jwtConfigurationBuilderRefresh.setType(type);
	}

	@Value("${app.security.jwt.refresh-token.duration}")
	public void setDurationJwtRefresh(int duration) {
		jwtConfigurationBuilderRefresh.setDuration(duration);
	}

	@Value("${app.security.jwt.refresh-token.rsa.private:}")
	public void setPrivateKeyPathJwtRefresh(String privateKeyPath) {
		jwtConfigurationBuilderRefresh.setPathPrivateKey(privateKeyPath);
	}

	@Value("${app.security.jwt.refresh-token.rsa.public:}")
	public void setPublicKeyPathJwtRefresh(String publicKeyPath) {
		jwtConfigurationBuilderRefresh.setPathPublicKey(publicKeyPath);
	}

	@Value("${app.security.jwt.refresh-token.secret-key:}")
	public void setSecretKeyJwtRefresh(String secretKey) {
		jwtConfigurationBuilderRefresh.setSecretKey(secretKey);
	}

	@Bean(name = BeansDefault.JWT_ACCESS)
	public JwtPort beanJwtAdapterAccess() {
		return jwtConfigurationBuilderAccess.build();
	}

	@Bean(name = BeansDefault.JWT_REFRESH)
	public JwtPort beansJwtAdapterRefresh() {
		return jwtConfigurationBuilderRefresh.build();
	}

}