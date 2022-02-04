package pe.izipay.common.core.domainobjects.tokens;

import lombok.*;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TokenDataResult {

    private Date valido_hasta;
    private String token;
}
