package shared.integrationtests;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserScope {

    protected String userId;
    protected String userName;
    protected String token;

}
