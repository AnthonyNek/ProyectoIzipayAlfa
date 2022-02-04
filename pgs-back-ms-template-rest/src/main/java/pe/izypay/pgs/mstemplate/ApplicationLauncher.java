package pe.izypay.pgs.mstemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@Slf4j
@EntityScan(basePackages = "pe.izipay.pgs.core.infrastructure.repositories.jpa.modelos")
@SpringBootApplication(exclude = {
		MongoAutoConfiguration.class,
		MongoDataAutoConfiguration.class
})
public class ApplicationLauncher {

	//private Environment environment;
/*
	@Autowired
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	@Value("${app.logs-separator}")
	private void setLogsSeparator(String logsSeparator) {
		AppRuntimeException.setSeparator(logsSeparator);
        log.info("Separador de logs agregado");
	}*/
	/*
	@PostConstruct
    public void init(){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        log.info("Zona horaria UTC agregada");
		log.info("Perfil activo: {}", TextHelper.join(",", (Object[]) this.environment.getActiveProfiles()));
    }*/
	
	public static void main(String[] args) {
		SpringApplication.run(ApplicationLauncher.class, args);
	}

}