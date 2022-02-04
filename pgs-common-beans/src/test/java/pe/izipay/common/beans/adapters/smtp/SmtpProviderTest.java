package pe.izipay.common.beans.adapters.smtp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.Properties;

import org.junit.jupiter.api.Test;

class SmtpProviderTest {
    @Test
    void testSetProtocol() {
        SmtpProvider smtpProvider = new SmtpProvider();
        smtpProvider.setProperties(new Properties());
        smtpProvider.setProtocol("Protocol");
        assertEquals(1, smtpProvider.getProperties().size());
        assertEquals(0, smtpProvider.getPort());
    }

    @Test
    void testSetAuth() {
        SmtpProvider smtpProvider = new SmtpProvider();
        smtpProvider.setProperties(new Properties());
        smtpProvider.setAuth(true);
        assertEquals(1, smtpProvider.getProperties().size());
        assertEquals(0, smtpProvider.getPort());
    }

    @Test
    void testSetTtlsEnabled() {
        SmtpProvider smtpProvider = new SmtpProvider();
        smtpProvider.setProperties(new Properties());
        smtpProvider.setTtlsEnabled(true);
        assertEquals(1, smtpProvider.getProperties().size());
        assertEquals(0, smtpProvider.getPort());
    }

    @Test
    void testSetDebug() {
        SmtpProvider smtpProvider = new SmtpProvider();
        smtpProvider.setProperties(new Properties());
        smtpProvider.setDebug(true);
        assertEquals(1, smtpProvider.getProperties().size());
        assertEquals(0, smtpProvider.getPort());
    }

    @Test
    void testLoadAllProperties() {
        SmtpProvider smtpProvider = new SmtpProvider();
        smtpProvider.setProperties(new Properties());
        smtpProvider.loadAllProperties();
        assertEquals(4, smtpProvider.getProperties().size());
        assertEquals(0, smtpProvider.getPort());
    }

    @Test
    void testLoadAllProperties2() {
        SmtpProvider smtpProvider = new SmtpProvider();
        Properties properties = new Properties();
        smtpProvider.loadAllProperties(properties);
        assertSame(properties, smtpProvider.getProperties());
    }
}

