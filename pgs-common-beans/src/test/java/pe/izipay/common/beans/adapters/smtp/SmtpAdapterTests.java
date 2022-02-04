package pe.izipay.common.beans.adapters.smtp;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import org.thymeleaf.TemplateEngine;
import pe.izipay.common.core.exceptions.AppRuntimeException;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootConfiguration
@SpringBootTest
class SmtpAdapterTests {			
	
	private static SmtpAdapter adapter;
	private static boolean mefBuildWithoutintegrations;
	
	@BeforeAll
	static void initialize() {
		System.getenv("MEF_BWI");
		mefBuildWithoutintegrations = false;
		log.info("MEF Build With Integrations (MEF_BWI): {}", false);
		mefBuildWithoutintegrations = !mefBuildWithoutintegrations;
		
		if(mefBuildWithoutintegrations) {
			return;
		}
		
		var smtpProvider = new SmtpProvider();
	    smtpProvider.setHost("smtp.gmail.com");
	    smtpProvider.setPort(587);	    
	    smtpProvider.setUsername("nsrtmmef@gmail.com");
	    smtpProvider.setPassword("NsrtmMef$");
		
		adapter = new SmtpAdapter(smtpProvider, null, null);		
	}
		
	@Test
	void test() {
		if(mefBuildWithoutintegrations) {
			return;
		}
		
		adapter.sendEmailHub(new String[] {"correo.falso@gmail.com"},
				"TEST BLUE POINT - HUB", "Hola Mundo...!!!");
		
		var exception = assertThrows(Exception.class, () -> adapter.sendEmailHub(new String[] {"correo_erroneo"},
				"TEST BLUE POINT - HUB", "Hola Mundo...!!!")
		);
		assertThat(exception).isNotNull();
	}		
	
	@Test
	void testSendEmail() {
		SmtpProvider provider = new SmtpProvider();
		SmtpAdapter smtpAdapter = new SmtpAdapter(provider, new TemplateEngine());
		assertThrows(AppRuntimeException.class, () -> smtpAdapter.sendEmail("",
				"Hello from the Dream World", "Not all who wander are lost"));
	}

	@Test
	void testSendEmailHub() {
		SmtpProvider provider = new SmtpProvider();
		SmtpAdapter smtpAdapter =  new SmtpAdapter(provider, new TemplateEngine());
		assertThrows(AppRuntimeException.class, () -> smtpAdapter
				.sendEmailHub(new String[]{""}, "Hello from the Dream World", "Not all who wander are lost"));
	}
}
