package pe.izipay.common.beans.repositories.audit;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseAuditable<T, E, R, M> implements IAuditable<T, E, R, M> {
    @NotNull
    protected E estado;
    @NotNull
    protected R usuario_creacion;
    @NotNull
    protected T fecha_creacion;
    protected R usuario_modificacion;
    protected T fecha_modificacion;
    protected R usuario_eliminacion;
    protected T fecha_eliminacion;
    protected M origen_cambio;
    
}