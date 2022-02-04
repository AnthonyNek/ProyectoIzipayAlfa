package pe.izipay.common.beans.adapters.smtp;

import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import pe.izipay.common.core.exceptions.AppRuntimeException;

public class SmtpAdapter extends BaseSmtpAdapter {
		
    private final JavaMailSender mailSender;    
    private final TemplateEngine templateEngine;

    public static JavaMailSender craeteJavaMailSender(SmtpProvider provider) {
    	JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
    	mailSenderImpl.setHost(provider.getHost());
	    mailSenderImpl.setPort(provider.getPort());	    
	    mailSenderImpl.setUsername(provider.getUsername());
	    mailSenderImpl.setPassword(provider.getPassword());
	    
	    provider.loadAllProperties(mailSenderImpl.getJavaMailProperties());
	    
	    return mailSenderImpl;
    }
    
    public SmtpAdapter(JavaMailSender mailSender, TemplateEngine templateEngine, TaskExecutor taskExecutor) {
    	super(taskExecutor);
    	this.mailSender = mailSender;
    	this.templateEngine = templateEngine;
    }
    
    public SmtpAdapter(SmtpProvider provider, TemplateEngine templateEngine, TaskExecutor taskExecutor) {
    	this(craeteJavaMailSender(provider), templateEngine, taskExecutor);
    }
    
    public SmtpAdapter(SmtpProvider provider, TemplateEngine templateEngine) {
    	this(craeteJavaMailSender(provider), templateEngine, createDefaultTaskExecutor());
    }
    
	@Override
	public void sendEmail(String to, String subject, String content) {
		try {
			new SmtpSender(mailSender, to).send(subject, content);	
		} catch (MessagingException ex) {
			throw new AppRuntimeException(ex);
		}
	}
	
	@Override
	public void sendEmail(String to, String subject, String template, Map<String,Object> variables) {		
		Context context = new Context();
		context.setVariables(variables);	    
	    sendEmail(to, subject, templateEngine.process(template, context));
	}		

	@Override
	public void sendEmailHub(String[] to, String subject, String content) {
		try {
			new SmtpSender(mailSender, to).send(subject, content);
		} catch (MessagingException ex) {
			throw new AppRuntimeException(ex);
		}
	}	

}
