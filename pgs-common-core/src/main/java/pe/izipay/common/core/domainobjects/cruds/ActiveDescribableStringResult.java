package pe.izipay.common.core.domainobjects.cruds;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import pe.izipay.common.core.domainobjects.BaseDescribable;
import pe.izipay.common.core.interfaces.activables.IActivableReader;

@Getter
@Setter
public class ActiveDescribableStringResult extends BaseDescribable.DescribableString implements IActivableReader {
    @JsonIgnore
    private byte estado;
}