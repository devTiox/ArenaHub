package arenahub.api.dto.request;

import arenahub.model.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountRequest {

    private String email;
    private String password;
    private AccountType accountType;

}
