package pe.izipay.common.beans.repositories.jpa;

import org.bson.types.ObjectId;
import pe.izipay.common.beans.repositories.audit.Auditable;
import pe.izipay.common.core.domainobjects.audit.UserAuditData;
import pe.izipay.common.core.types.DataStatusType;

import java.util.Date;
import java.util.function.Function;

public abstract class CrudJPARepository<T extends Auditable, K> extends BaseJPARepository<T, K> {
	
	@Override
	public T getModelById(K id) {
		var model = super.getModelById(id);
		if(DataStatusType.DELETED.match(model.getEstado())) {
			throw EXCEPTION_RECORD_NOT_FOUND;
		}
		return model;
	}		
	
    public void updateCreationAudit(T model, UserAuditData user) {
        model.setFecha_creacion(new Date());
        model.setUsuario_creacion(user.getUsuarioNombre());
        model.setOrigen_cambio(user.getTipoOrigen().toString());
    }

    public void updateModificationAudit(T model, UserAuditData user) {
        model.setFecha_modificacion(new Date());
        model.setUsuario_modificacion(user.getUsuarioNombre());
        model.setOrigen_cambio(user.getTipoOrigen().toString());
    }

    public void updateDeletionAudit(T model, UserAuditData user) {
        model.setEstado((byte) DataStatusType.DELETED.ordinal());
        model.setFecha_eliminacion(new Date());
        model.setUsuario_eliminacion(user.getUsuarioNombre());
        model.setOrigen_cambio(user.getTipoOrigen().toString());
    }

    public void updateActivationAudit(boolean active, T model, UserAuditData user) {
        model.setEstado((byte) ((active ? DataStatusType.ENABLED : DataStatusType.DISABLED).ordinal()));
        updateModificationAudit(model, user);
    }
    public void updateActivationAudit(T model, UserAuditData user) {
        model.setEstado((byte) DataStatusType.ENABLED.ordinal());
        updateModificationAudit(model, user);
    }
    
    public void updateDeactivationAudit(T model, UserAuditData user) {
        model.setEstado((byte) DataStatusType.DISABLED.ordinal());
        updateModificationAudit(model, user);
    }
    
    public <P> P getProjectionById(K id, Function<K, P> callBack) {
		var projection = callBack.apply(id);
		if(projection == null) {
			throw EXCEPTION_RECORD_NOT_FOUND;
		}
		return projection;
	}
	
	public String generateObjectId() {
		return new ObjectId().toString();
	}
    
}