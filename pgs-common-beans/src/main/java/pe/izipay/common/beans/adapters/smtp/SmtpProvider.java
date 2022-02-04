package pe.izipay.common.beans.adapters.smtp;

import java.util.Properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SmtpProvider {
	
	private static final String DEFAULT_PROTOCOL = "mail.transport.protocol";
	private static final String DEFAULT_AUTH = "mail.smtp.auth";
	private static final String DEFAULT_TTLS_ENABLED = "mail.smtp.starttls.enable";
	private static final String DEFAULT_DEBUG = "mail.debug";
	
	private String host;
	private int port;
	private String username;
	private String password;
	private Properties properties;			
	
	public void setProtocol(String protocol) {
		properties.put(DEFAULT_PROTOCOL, protocol);
	}
	
	public void setAuth(boolean auth) {
		properties.put(DEFAULT_AUTH, Boolean.toString(auth));
	}
	
	public void setTtlsEnabled(boolean ttlsEnabled) {
		properties.put(DEFAULT_TTLS_ENABLED, Boolean.toString(ttlsEnabled));
	}
	
	public void setDebug(boolean debug) {
		properties.put(DEFAULT_DEBUG, Boolean.toString(debug));
	}	
	
	public void loadAllProperties(Properties properties) {
		setProperties(properties);
		loadAllProperties();
	}
	
	public void loadAllProperties() {		
		setProtocol("smtp");
		setAuth(true);
		setTtlsEnabled(true);
		setDebug(true);
	}
}
