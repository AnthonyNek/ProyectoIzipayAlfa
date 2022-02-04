package pe.izipay.common.beans.adapters.smtp;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class SmtpSender {

	private final JavaMailSender mailSender;
	
	private MimeMessage message;
	private MimeMessageHelper helper;
	
	protected SmtpSender(JavaMailSender mailSender) throws MessagingException {		
		this.mailSender = mailSender;
		this.message = mailSender.createMimeMessage();
		this.helper = new MimeMessageHelper(message, true);
	}
	
	public SmtpSender(JavaMailSender mailSender, String to) throws MessagingException {
		this(mailSender);
		this.helper.setTo(to);
	}
	
	public SmtpSender(JavaMailSender mailSender, String[] to) throws MessagingException {
		this(mailSender);
		this.helper.setTo(to);
	}
	
	public void send(String subject, String content) throws MessagingException {
		helper.setSubject(subject);
		helper.setText(content, true);
		mailSender.send(message);
	}
}
