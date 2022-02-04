package pe.izipay.common.beans.repositories.audit;

public interface IAuditable<T, E, R, M> {
	
    E getEstado();
	void setEstado(E estado);
	
	R getUsuario_creacion();
	void setUsuario_creacion(R usuario_creacion);
	
	T getFecha_creacion();	
	void setFecha_creacion(T fecha_creacion);
	
	R getUsuario_modificacion();
	void setUsuario_modificacion(R usuario_modicacion);
	
	T getFecha_modificacion();
	void setFecha_modificacion(T fecha_modicacion);
	
	R getUsuario_eliminacion();
	void setUsuario_eliminacion(R usuario_eliminacion);
	
	T getFecha_eliminacion();
	void setFecha_eliminacion(T fecha_eliminacion);
	
	M getOrigen_cambio();
	void setOrigen_cambio(M origen_cambio);
	
}