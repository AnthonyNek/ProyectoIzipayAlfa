package pe.izypay.pgs.mstemplate.infrastructure.middlewares;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import pe.izipay.common.beans.middlewares.mongodb.MongoDbConvertersBuilder;


@Configurable
public class MongoDbMiddleware {

	@Bean
	public MongoCustomConversions customConversions() {
		return MongoDbConvertersBuilder.build();
	}
}
