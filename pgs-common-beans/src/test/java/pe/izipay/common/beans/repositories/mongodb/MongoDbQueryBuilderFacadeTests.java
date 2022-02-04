package pe.izipay.common.beans.repositories.mongodb;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import pe.izipay.common.beans.repositories.mongodb.MongoDbQueryBuilderFacade;
import pe.izipay.common.core.domainobjects.ranges.DatesRangePayload.DefaultDatesRangePayload;
import pe.izipay.common.core.domainobjects.ranges.Range;

@Slf4j
@SpringBootConfiguration
@SpringBootTest
class MongoDbQueryBuilderFacadeTests {
	
	private static final String valueObjectId = "610c4382652bdd2acb6dc7fd";
	
	private MongoDbQueryBuilderFacade builderFacade;
	
	@BeforeEach
	void byteValue() {
		builderFacade = new MongoDbQueryBuilderFacade();		
	}
	
	@Test
	void addNotDeleted() {		
		assertThat(builderFacade.addNotDeleted().isEmpty()).isFalse();		
	}	

	@Test
	void addObjectId() {		
		assertThat(builderFacade.addObjectId("a", "").isEmpty()).isTrue();
		assertThat(builderFacade.addObjectId("a", null).isEmpty()).isTrue();
		assertThat(builderFacade.addObjectId("a", valueObjectId).isEmpty()).isFalse();
	}
	
	@Test
	void addContainsInsentive() {
		assertThat(builderFacade.addContainsInsentive("a", "").isEmpty()).isTrue();
		assertThat(builderFacade.addContainsInsentive("a", null).isEmpty()).isTrue();
		assertThat(builderFacade.addContainsInsentive("a", "1").isEmpty()).isFalse();
	}
	
	@Test
	void addAllContainsInsentive() {
		assertThat(builderFacade.addAllContainsInsentive("", "a").isEmpty()).isTrue();
		assertThat(builderFacade.addAllContainsInsentive(null, "a").isEmpty()).isTrue();
		assertThat(builderFacade.addAllContainsInsentive("1", "a").isEmpty()).isFalse();
	}

	@Test
	void build() {		
		assertThat(builderFacade.build()).isNotNull();		
	}
	
	@Test
	void addIfHasValue() {
		assertThat(builderFacade.addIfHasValue("a", "").isEmpty()).isTrue();
		assertThat(builderFacade.addIfHasValue("a", null).isEmpty()).isTrue();
		assertThat(builderFacade.addIfHasValue("a", "1").isEmpty()).isFalse();
	}
	
	@Test
	void addAll() {
		var set = new HashSet<ObjectId>();
		assertThat(builderFacade.addAll("a", set).isEmpty()).isTrue();
		assertThat(builderFacade.addAll("a", null).isEmpty()).isTrue();
		
		set.add(new ObjectId());
		assertThat(builderFacade.addAll("a", set).isEmpty()).isFalse();		
	}
	
	@Test
	void addAllObjectId() {
		var list = new ArrayList<String>();
		assertThat(builderFacade.addAllObjectId("a", list).isEmpty()).isTrue();
		assertThat(builderFacade.addAllObjectId("a", null).isEmpty()).isTrue();
		
		list.add(valueObjectId);
		assertThat(builderFacade.addAllObjectId("a", list).isEmpty()).isFalse();		
	}
	
	@Test
	void addRange() {
		var payload = new DefaultDatesRangePayload();
		assertThat(builderFacade.addRange("a", payload.getRango_fechas()).isEmpty()).isTrue();
		
		payload.setRango_fechas(new Range<>());
		assertThat(builderFacade.addRange("a", payload.getRango_fechas()).isEmpty()).isTrue();
		
		var currentTime = System.currentTimeMillis();
		payload.getRango_fechas().setDesde((new Date(currentTime)));		
		assertThat(builderFacade.addRange("a", payload.getRango_fechas()).isEmpty()).isTrue();
		
		payload.getRango_fechas().setHasta(new Date(currentTime - 1));
		assertThat(builderFacade.addRange("a", payload.getRango_fechas()).isEmpty()).isTrue();
		
		payload.getRango_fechas().setHasta(new Date(currentTime + 60000));
		assertThat(builderFacade.addRange("a", payload.getRango_fechas()).isEmpty()).isFalse();
		
		log.info("Range Query: {}", builderFacade.build());
	}
}
