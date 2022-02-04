package pe.izipay.common.core.helpers;

import static org.mockito.Mockito.anyByte;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import pe.izipay.common.core.interfaces.activables.IActivableWriter;

class DomainHelperTest {
    @Test
    void testSetActivo() {
        IActivableWriter iActivableWriter = mock(IActivableWriter.class);
        doNothing().when(iActivableWriter).setEstado(anyByte());
        DomainHelper.setActivo(iActivableWriter, true);
        verify(iActivableWriter).setEstado(anyByte());
    }

    @Test
    void testSetActivo2() {
        IActivableWriter iActivableWriter = mock(IActivableWriter.class);
        doNothing().when(iActivableWriter).setEstado(anyByte());
        DomainHelper.setActivo(iActivableWriter, null);
        verify(iActivableWriter).setEstado(anyByte());
    }

    @Test
    void testSetActivo3() {
        IActivableWriter iActivableWriter = mock(IActivableWriter.class);
        doNothing().when(iActivableWriter).setEstado(anyByte());
        DomainHelper.setActivo(iActivableWriter, false);
        verify(iActivableWriter).setEstado(anyByte());
    }
}

