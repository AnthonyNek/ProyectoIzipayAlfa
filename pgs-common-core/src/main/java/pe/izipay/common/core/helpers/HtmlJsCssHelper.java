package pe.izipay.common.core.helpers;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class HtmlJsCssHelper {		
	
	public static String bolt(String inner) {
		return String.format("<b>%s</b>", inner);
	}
	
	public static String link(String href, String label) {
		return String.format("<a href=\"%s\">%s<a/>", href, label);
	}		
	
	public static String showLocalDate(LocalDateTime localDateTime, String variable) {		
		return new StringBuilder()
				.append("<span id=\"").append(variable).append("\">").append(DateTimeHelper.formatJsISO(localDateTime)).append("</span>")
				.append("<script>")
				.append("let ").append(variable).append("Selector = document.getElementById(\"")
					.append(variable).append("\");")
				.append("let ").append(variable).append("Date = new Date(Date.UTC(")
					.append(localDateTime.getYear()).append(", ")
					.append(localDateTime.getMonth().ordinal()).append(", ")
					.append(localDateTime.getDayOfMonth()).append(", ")
					.append(localDateTime.getHour()).append(", ")
					.append(localDateTime.getMinute()).append(", ")
					.append(localDateTime.getSecond())
					.append("));")
				.append(variable).append("Selector.textContent = ").append(variable)
					.append("Date.toLocaleString() + ' ' + Intl.DateTimeFormat().resolvedOptions().timeZone;")
				.append("</script>")
				.toString();
	}
	
	public static String showLocalDate(Date date, String variable) {		
		return showLocalDate(DateTimeHelper.convertToLocalDateTime(date), variable);				
	}
}