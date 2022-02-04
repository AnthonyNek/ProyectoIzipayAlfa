package pe.izipay.common.beans.adapters.smtp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.core.task.TaskExecutor;
import org.thymeleaf.TemplateEngine;

class BaseSmtpAdapterTest {
    @Test
    void testCreateDefaultTaskExecutor() {
        TaskExecutor actualCreateDefaultTaskExecutorResult = BaseSmtpAdapter.createDefaultTaskExecutor();
        actualCreateDefaultTaskExecutorResult.execute(mock(Runnable.class));
        assertTrue(
                actualCreateDefaultTaskExecutorResult instanceof org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor);
    }

    @Test
    void testSendEmailSafe() {
        SmtpProvider provider = new SmtpProvider();
        assertFalse((new SmtpAdapter(provider, new TemplateEngine())).sendEmailSafe("alicia.encalada@example.org",
                "Hello from the Dreaming Spires", "Not all who wander are lost"));
    }

    @Test
    void testSendEmailSafe2() {
        SmtpProvider provider = new SmtpProvider();
        SmtpAdapter smtpAdapter = new SmtpAdapter(provider, new TemplateEngine());
        assertEquals(0,
                smtpAdapter.sendEmailSafe(new ArrayList<>(), "Hello from the Dreaming Spires", "Not all who wander are lost"));
    }

    @Test
    void testSendEmailSafe3() {
        SmtpProvider provider = new SmtpProvider();
        SmtpAdapter smtpAdapter = new SmtpAdapter(provider, new TemplateEngine());

        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        assertEquals(0,
                smtpAdapter.sendEmailSafe(stringList, "Hello from the Dreaming Spires", "Not all who wander are lost"));
    }

    @Test
    void testSendEmailSafe4() {
        SmtpProvider provider = new SmtpProvider();
        SmtpAdapter smtpAdapter = new SmtpAdapter(provider, new TemplateEngine());
        IEmail iEmail = mock(IEmail.class);
        when(iEmail.getEmail()).thenReturn("alicia.encalada@example.org");
        assertFalse(smtpAdapter.sendEmailSafe(iEmail, "Hello from the Dreaming Spires", "Not all who wander are lost"));
        verify(iEmail).getEmail();
    }

    @Test
    void testSendEmailAsync() {
        SmtpProvider provider = new SmtpProvider();
        assertDoesNotThrow(() -> (new SmtpAdapter(provider, new TemplateEngine())).sendEmailAsync("alicia.encalada@example.org",
                "Hello from the Dreaming Spires", "Not all who wander are lost"));
    }

    @Test
    void testSendEmailAsync2() {
        TaskExecutor taskExecutor = mock(TaskExecutor.class);
        doNothing().when(taskExecutor).execute(any());
        SmtpProvider provider = new SmtpProvider();
        (new SmtpAdapter(provider, new TemplateEngine(), taskExecutor)).sendEmailAsync("alicia.encalada@example.org",
                "Hello from the Dreaming Spires", "Not all who wander are lost");
        verify(taskExecutor).execute(any());
    }

    @Test
    void testSendEmailAsync3() {
        SmtpProvider provider = new SmtpProvider();
        SmtpAdapter smtpAdapter = new SmtpAdapter(provider, new TemplateEngine());
        assertDoesNotThrow(() -> smtpAdapter.sendEmailAsync("alicia.encalada@example.org", "Hello from the Dreaming Spires", "Template",
                new HashMap<>()));
    }

    @Test
    void testSendEmailAsync4() {
        TaskExecutor taskExecutor = mock(TaskExecutor.class);
        doNothing().when(taskExecutor).execute(any());
        SmtpProvider provider = new SmtpProvider();
        SmtpAdapter smtpAdapter = new SmtpAdapter(provider, new TemplateEngine(), taskExecutor);
        smtpAdapter.sendEmailAsync("alice.liddell@example.org", "Hello from the Dreaming Spires", "Template",
                new HashMap<>());
        verify(taskExecutor).execute(any());
    }

    @Test
    void testSendEmailAsync5() {
        SmtpProvider provider = new SmtpProvider();
        SmtpAdapter smtpAdapter = new SmtpAdapter(provider, new TemplateEngine());
        assertDoesNotThrow(() -> smtpAdapter.sendEmailAsync(new ArrayList<>(), "Hello from the Dreaming Spires", "Not all who wander are lost"));
    }

    @Test
    void testSendEmailAsync6() {
        TaskExecutor taskExecutor = mock(TaskExecutor.class);
        doNothing().when(taskExecutor).execute(any());
        SmtpProvider provider = new SmtpProvider();
        SmtpAdapter smtpAdapter = new SmtpAdapter(provider, new TemplateEngine(), taskExecutor);
        smtpAdapter.sendEmailAsync(new ArrayList<>(), "Hello from the Dreaming Spires", "Not all who wander are lost");
        verify(taskExecutor).execute(any());
    }

    @Test
    void testSendEmailAsync7() {
        SmtpProvider provider = new SmtpProvider();
        assertDoesNotThrow(() -> (new SmtpAdapter(provider, new TemplateEngine())).sendEmailAsync(mock(IEmail.class),
                "Hello from the Dreaming Spires", "Not all who wander are lost"));
    }

    @Test
    void testSendEmailAsync8() {
        TaskExecutor taskExecutor = mock(TaskExecutor.class);
        doNothing().when(taskExecutor).execute(any());
        SmtpProvider provider = new SmtpProvider();
        (new SmtpAdapter(provider, new TemplateEngine(), taskExecutor)).sendEmailAsync(mock(IEmail.class),
                "Hello from the Dreaming Spires", "Not all who wander are lost");
        verify(taskExecutor).execute(any());
    }

    @Test
    void testSendEmailObjectHubSafe() {
        SmtpProvider provider = new SmtpProvider();
        SmtpAdapter smtpAdapter = new SmtpAdapter(provider, new TemplateEngine());
        assertFalse(smtpAdapter.sendEmailObjectHubSafe(new ArrayList<>(), "Hello from the Dreaming Spires",
                "Not all who wander are lost"));
    }

    @Test
    void testSendEmailObjectHubSafe2() {
        SmtpProvider provider = new SmtpProvider();
        SmtpAdapter smtpAdapter = new SmtpAdapter(provider, new TemplateEngine());
        IEmail iEmail = mock(IEmail.class);
        when(iEmail.getEmail()).thenReturn("alicia.encalada@example.org");

        ArrayList<IEmail> iEmailList = new ArrayList<>();
        iEmailList.add(iEmail);
        assertFalse(smtpAdapter.sendEmailObjectHubSafe(iEmailList, "Hello from the Dreaming Spires",
                "Not all who wander are lost"));
        verify(iEmail).getEmail();
    }

    @Test
    void testSendEmailObjectHubAsync() {
        SmtpProvider provider = new SmtpProvider();
        SmtpAdapter smtpAdapter = new SmtpAdapter(provider, new TemplateEngine());
        assertDoesNotThrow(() -> smtpAdapter.sendEmailObjectHubAsync(new ArrayList<>(), "Hello from the Dreaming Spires",
                "Not all who wander are lost"));
    }

    @Test
    void testSendEmailObjectHubAsync2() {
        TaskExecutor taskExecutor = mock(TaskExecutor.class);
        doNothing().when(taskExecutor).execute(any());
        SmtpProvider provider = new SmtpProvider();
        SmtpAdapter smtpAdapter = new SmtpAdapter(provider, new TemplateEngine(), taskExecutor);
        smtpAdapter.sendEmailObjectHubAsync(new ArrayList<>(), "Hello from the Dreaming Spires",
                "Not all who wander are lost");
        verify(taskExecutor).execute(any());
    }

    @Test
    void testSendEmailObject() {
        SmtpProvider provider = new SmtpProvider();
        SmtpAdapter smtpAdapter = new SmtpAdapter(provider, new TemplateEngine());
        assertDoesNotThrow(() -> smtpAdapter.sendEmailObject(new ArrayList<>(), "Hello from the Dreaming Spires", "Not all who wander are lost"));
    }

    @Test
    void testSendEmailObjectSafe() {
        SmtpProvider provider = new SmtpProvider();
        SmtpAdapter smtpAdapter = new SmtpAdapter(provider, new TemplateEngine());
        assertEquals(0, smtpAdapter.sendEmailObjectSafe(new ArrayList<>(), "Hello from the Dreaming Spires",
                "Not all who wander are lost"));
    }

    @Test
    void testSendEmailObjectSafe2() {
        SmtpProvider provider = new SmtpProvider();
        SmtpAdapter smtpAdapter = new SmtpAdapter(provider, new TemplateEngine());
        IEmail iEmail = mock(IEmail.class);
        when(iEmail.getEmail()).thenReturn("alicia.encalada@example.org");

        ArrayList<IEmail> iEmailList = new ArrayList<>();
        iEmailList.add(iEmail);
        assertEquals(0,
                smtpAdapter.sendEmailObjectSafe(iEmailList, "Hello from the Dreaming Spires", "Not all who wander are lost"));
        verify(iEmail).getEmail();
    }

    @Test
    void testSendEmailObjectAsync() {
        SmtpProvider provider = new SmtpProvider();
        SmtpAdapter smtpAdapter = new SmtpAdapter(provider, new TemplateEngine());
        assertDoesNotThrow(() -> smtpAdapter.sendEmailObjectAsync(new ArrayList<>(), "Hello from the Dreaming Spires",
                "Not all who wander are lost"));
    }

    @Test
    void testSendEmailObjectAsync2() {
        TaskExecutor taskExecutor = mock(TaskExecutor.class);
        doNothing().when(taskExecutor).execute(any());
        SmtpProvider provider = new SmtpProvider();
        SmtpAdapter smtpAdapter = new SmtpAdapter(provider, new TemplateEngine(), taskExecutor);
        smtpAdapter.sendEmailObjectAsync(new ArrayList<>(), "Hello from the Dreaming Spires",
                "Not all who wander are lost");
        verify(taskExecutor).execute(any());
    }

    @Test
    void testSendEmailHubSafe() {
        SmtpProvider provider = new SmtpProvider();
        SmtpAdapter smtpAdapter = new SmtpAdapter(provider, new TemplateEngine());
        assertFalse(smtpAdapter.sendEmailHubSafe(new ArrayList<>(), "Hello from the Dreaming Spires",
                "Not all who wander are lost"));
    }

    @Test
    void testSendEmailHubSafe2() {
        SmtpProvider provider = new SmtpProvider();
        assertFalse((new SmtpAdapter(provider, new TemplateEngine())).sendEmailHubSafe(
                new String[]{"alicia.encalada@example.org"}, "Hello from the Dreaming Spires", "Not all who wander are lost"));
    }

    @Test
    void testSendEmailHubAsync() {
        SmtpProvider provider = new SmtpProvider();
        SmtpAdapter smtpAdapter = new SmtpAdapter(provider, new TemplateEngine());
        assertDoesNotThrow(() -> smtpAdapter.sendEmailHubAsync(new ArrayList<>(), "Hello from the Dreaming Spires", "Not all who wander are lost"));
    }

    @Test
    void testSendEmailHubAsync2() {
        TaskExecutor taskExecutor = mock(TaskExecutor.class);
        doNothing().when(taskExecutor).execute(any());
        SmtpProvider provider = new SmtpProvider();
        SmtpAdapter smtpAdapter = new SmtpAdapter(provider, new TemplateEngine(), taskExecutor);
        smtpAdapter.sendEmailHubAsync(new ArrayList<>(), "Hello from the Dreaming Spires", "Not all who wander are lost");
        verify(taskExecutor).execute(any());
    }

    @Test
    void testSendEmailHubAsync3() {
        SmtpProvider provider = new SmtpProvider();
        assertDoesNotThrow(() -> (new SmtpAdapter(provider, new TemplateEngine())).sendEmailHubAsync(new String[]{"alicia.encalada@example.org"},
                "Hello from the Dreaming Spires", "Not all who wander are lost"));
    }

    @Test
    void testSendEmailHubAsync4() {
        TaskExecutor taskExecutor = mock(TaskExecutor.class);
        doNothing().when(taskExecutor).execute(any());
        SmtpProvider provider = new SmtpProvider();
        (new SmtpAdapter(provider, new TemplateEngine(), taskExecutor)).sendEmailHubAsync(
                new String[]{"alicia.encalada@example.org"}, "Hello from the Dreaming Spires", "Not all who wander are lost");
        verify(taskExecutor).execute(any());
    }

    @Test
    void testSendEmail() {
        SmtpProvider provider = new SmtpProvider();
        SmtpAdapter smtpAdapter = new SmtpAdapter(provider, new TemplateEngine());
        assertDoesNotThrow(() -> smtpAdapter.sendEmail(new ArrayList<>(), "Hello from the Dreaming Spires", "Not all who wander are lost"));
    }
}

