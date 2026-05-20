package arenahub.api.dto.response;

import arenahub.model.Account;
import arenahub.model.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AccountResponse {
    private Long id;
    private String email;
    private AccountType accountType;
    private boolean active;

    public static AccountResponse from(Account account) {
        return new AccountResponse(
                account.getId(),
                account.getEmail(),
                account.getType(),
                account.isActive()
        );
    }
}
