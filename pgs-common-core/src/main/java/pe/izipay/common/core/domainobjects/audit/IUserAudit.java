package pe.izipay.common.core.domainobjects.audit;

public interface IUserAudit<T> {

    T getUsuarioId();
    String getUsuarioNombre();
}