package pe.izipay.common.core.domainobjects.tokens;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TokenResult {

    private TokenDataResult acceso;
    private TokenDataResult actualizacion;
}
