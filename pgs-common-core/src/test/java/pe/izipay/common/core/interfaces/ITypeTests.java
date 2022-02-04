package pe.izipay.common.core.interfaces;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import pe.izipay.common.core.types.DataStatusType;

@SpringBootConfiguration
@SpringBootTest
class ITypeTests {

	private static final IType type = DataStatusType.ENABLED;
	
	@Test
	void byteValue() {
		assertThat(type.byteValue()).isEqualTo((byte)1);
	}
	
	@Test
	void match() {
		assertThat(type.match(1)).isTrue();
		assertThat(type.match(2)).isFalse();
		assertThat(type.match((byte)1)).isTrue();
		assertThat(type.match((byte)2)).isFalse();
	}	

	@Test
	void equalsType() {
		assertThat(type.equalsType(DataStatusType.ENABLED.name())).isTrue();
	}
	
	@Test
	void filter() {
		assertThat(type.filter()).isEqualTo(DataStatusType.ENABLED.name());
	}

}
