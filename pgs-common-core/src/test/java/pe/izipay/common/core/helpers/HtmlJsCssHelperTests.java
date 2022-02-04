package pe.izipay.common.core.helpers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class HtmlJsCssHelperTests {

	@Test
	void bolt() {
		assertThat(HtmlJsCssHelper.bolt("")).isEqualTo("<b></b>");		
	}
	
	@Test
	void showLocalDate() {
		var dateTime = LocalDateTime.of(2021, 12, 01, 15, 30, 28);
		var date = DateTimeHelper.convertToDate(dateTime);
		var js = HtmlJsCssHelper.showLocalDate(date, "test");
		log.info(js);
		assertThat(js).contains("testSelector", "testDate");
	}

	@Test
	void testLink() {
		assertEquals("<a href=\"Href\">Label<a/>", HtmlJsCssHelper.link("Href", "Label"));
	}

}
