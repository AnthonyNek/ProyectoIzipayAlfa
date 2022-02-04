package pe.izipay.common.beans.adapters.smtp;

import java.util.Collection;
import java.util.Map;

import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public abstract class BaseSmtpAdapter implements SmtpPort {

	private final TaskExecutor taskExecutor;
	
	 public static TaskExecutor createDefaultTaskExecutor() {
		    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		    executor.setCorePoolSize(5);
		    executor.setMaxPoolSize(5);
		    executor.setQueueCapacity(500);
		    executor.setThreadNamePrefix("SmtpLookup-");
		    executor.initialize();
		    return executor;
	}
	
	@Override
	public boolean sendEmailSafe(String to, String subject, String content) {
		try {
			sendEmail(to, subject, content);
			return true;
		} catch (Exception ex) {
			log.warn("SMTP", ex);
			return false;
		}
	}
	
	@Override
	public void sendEmailAsync(String to, String subject, String content) {
		taskExecutor.execute(() -> sendEmailSafe(to, subject, content));		
	}
	
	@Override
	public void sendEmail(IEmail to, String subject, String content) {
		sendEmail(to.getEmail(), subject, content);
	}
	
	@Override
	public boolean sendEmailSafe(IEmail to, String subject, String content) {
		try {
			sendEmail(to.getEmail(), subject, content);
			return true;
		} catch (Exception ex) {
			log.warn("SMTP", ex);
			return false;
		}
	}
		
	@Override
	public void sendEmailAsync(IEmail to, String subject, String content) {
		taskExecutor.execute(() -> sendEmailSafe(to, subject, content));		
	}

	@Override
	public boolean sendEmailSafe(String to, String subject, String template, Map<String, Object> variables) {
		try {
			sendEmail(to, subject, template, variables);
			return true;
		} catch (Exception ex) {
			log.warn("SMTP", ex);
			return false;
		}
	}
	
	@Override
	public void sendEmailAsync(String to, String subject, String template, Map<String, Object> variables) {
		taskExecutor.execute(() -> sendEmail(to, subject, template, variables));		
	}

	@Override
	public boolean sendEmailHubSafe(String[] to, String subject, String content) {
		try {
			sendEmailHub(to, subject, content);
			return true;
		} catch (Exception ex) {
			log.warn("SMTP", ex);
			return false;
		}
	}
	
	@Override
	public void sendEmailHubAsync(String[] to, String subject, String content) {
		taskExecutor.execute(() -> sendEmailHubSafe(to, subject, content));		
	}

	@Override
	public void sendEmailHub(Collection<String> to, String subject, String content) {
		sendEmailHub(to.toArray(String[]::new), subject, content);
	}

	@Override
	public boolean sendEmailHubSafe(Collection<String> to, String subject, String content) {
		try {
			sendEmailHub(to, subject, content);
			return true;
		} catch (Exception ex) {
			log.warn("SMTP", ex);
			return false;
		}
	}
	
	@Override
	public void sendEmailHubAsync(Collection<String> to, String subject, String content) {
		taskExecutor.execute(() -> sendEmailHubSafe(to, subject, content));		
	}

	@Override
	public void sendEmail(Collection<String> to, String subject, String content) {
		for (String item : to) {
			sendEmail(item, subject, content);
		}
	}

	@Override
	public int sendEmailSafe(Collection<String> to, String subject, String content) {
		int count = 0;
		for (String item : to) {
			try {
				sendEmail(item, subject, content);
				count++;
			} catch (Exception ex) {
				log.warn("SMTP", ex);
			}
		}
		return count;
	}
	
	@Override
	public void sendEmailAsync(Collection<String> to, String subject, String content) {
		taskExecutor.execute(() -> sendEmailSafe(to, subject, content));
	}
	
	@Override
	public <E extends IEmail> void sendEmailObjectHub(Collection<E> to, String subject, String content) {		
		sendEmailHub(IEmail.toArray(to), subject, content);
	}
	
	@Override
	public <E extends IEmail> boolean sendEmailObjectHubSafe(Collection<E> to, String subject, String content) {
		try {
			sendEmailObjectHub(to, subject, content);
			return true;
		} catch (Exception ex) {
			log.warn("SMTP", ex);
			return false;
		}
	}
		
	@Override
	public <E extends IEmail> void sendEmailObjectHubAsync(Collection<E> to, String subject, String content) {
		taskExecutor.execute(()-> sendEmailObjectHubSafe(to, subject, content));
	}
	
	@Override
	public <E extends IEmail> void sendEmailObject(Collection<E> to, String subject, String content) {		
		for (IEmail item : to) {
			sendEmail(item, subject, content);
		}
	}
	
	@Override
	public <E extends IEmail> int sendEmailObjectSafe(Collection<E> to, String subject, String content) {
		int count = 0;
		for (IEmail item : to) {
			try {
				sendEmail(item, subject, content);
				count++;
			} catch (Exception ex) {
				log.warn("SMTP", ex);
			}
		}
		return count;
	}
		
	@Override
	public <E extends IEmail> void sendEmailObjectAsync(Collection<E> to, String subject, String content) {
		taskExecutor.execute(() -> sendEmailObjectSafe(to, subject, content));		
	}

}