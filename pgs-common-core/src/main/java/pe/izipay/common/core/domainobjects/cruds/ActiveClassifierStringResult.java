package pe.izipay.common.core.domainobjects.cruds;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import pe.izipay.common.core.interfaces.activables.IActivableReader;
import pe.izipay.common.core.domainobjects.BaseClassifier;

@Getter
@Setter
public class ActiveClassifierStringResult extends BaseClassifier.ObjectLightString implements IActivableReader {
    @JsonIgnore
    private byte estado;
}