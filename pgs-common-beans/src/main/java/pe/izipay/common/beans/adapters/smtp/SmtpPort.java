package pe.izipay.common.beans.adapters.smtp;

import java.util.Collection;
import java.util.Map;

public interface SmtpPort {
		
	void sendEmail(String to, String subject, String content);
	boolean sendEmailSafe(String to, String subject, String content);
	void sendEmailAsync(String to, String subject, String content);
	
	void sendEmail(IEmail to, String subject, String content);
	boolean sendEmailSafe(IEmail to, String subject, String content);
	void sendEmailAsync(IEmail to, String subject, String content);
	
	void sendEmail(String to, String subject, String template, Map<String,Object> variables);
	boolean sendEmailSafe(String to, String subject, String template, Map<String,Object> variables);
	void sendEmailAsync(String to, String subject, String template, Map<String,Object> variables);
		
	void sendEmailHub(String[] to, String subject, String content);
	boolean sendEmailHubSafe(String[] to, String subject, String content);
	void sendEmailHubAsync(String[] to, String subject, String content);
	
	void sendEmailHub(Collection<String> to, String subject, String content);
	boolean sendEmailHubSafe(Collection<String> to, String subject, String content);
	void sendEmailHubAsync(Collection<String> to, String subject, String content);
	
	void sendEmail(Collection<String> to, String subject, String content);
	int sendEmailSafe(Collection<String> to, String subject, String content);
	void sendEmailAsync(Collection<String> to, String subject, String content);
	
	<E extends IEmail> void sendEmailObjectHub(Collection<E> to, String subject, String content);
	<E extends IEmail> boolean sendEmailObjectHubSafe(Collection<E> to, String subject, String content);
	<E extends IEmail> void sendEmailObjectHubAsync(Collection<E> to, String subject, String content);
	
	<E extends IEmail> void sendEmailObject(Collection<E> to, String subject, String content);
	<E extends IEmail> int sendEmailObjectSafe(Collection<E> to, String subject, String content);
	<E extends IEmail> void sendEmailObjectAsync(Collection<E> to, String subject, String content);
}