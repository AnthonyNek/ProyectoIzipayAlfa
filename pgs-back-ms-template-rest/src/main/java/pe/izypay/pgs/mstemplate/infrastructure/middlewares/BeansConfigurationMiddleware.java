package pe.izypay.pgs.mstemplate.infrastructure.middlewares;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;

import lombok.RequiredArgsConstructor;
import pe.izipay.common.beans.adapters.smtp.SmtpAdapter;
import pe.izipay.common.beans.middlewares.JsonConversionDataConfiguration;

@RequiredArgsConstructor
@Configuration
public class BeansConfigurationMiddleware {

	private final JsonConversionDataConfiguration configuration = new JsonConversionDataConfiguration();
	
	private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;
    private final TaskExecutor taskExecutor;
	
	@Value("${app.json.date-format}")
	public void setDateFormat(String dateFormat) {
		configuration.setDateFormat(dateFormat);		
	}
	
	@Value("${app.json.time-format}")
	public void setTimeFormat(String timeFormat) {
		configuration.setTimeFormat(timeFormat);		
	}
	
	@Value("${spring.jackson.date-format}")
	public void setDateTimeFormat(String dateTimeFormat) {
		configuration.setDateTimeFormat(dateTimeFormat);		
	}	
	
	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
		return configuration::load;
	}
	
	@Bean
	public SmtpAdapter beanSmtpAdapter() {
		return new SmtpAdapter(mailSender, templateEngine, taskExecutor);
	}
}