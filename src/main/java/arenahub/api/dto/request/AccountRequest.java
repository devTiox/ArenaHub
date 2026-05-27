package arenahub.api.dto.request;

import arenahub.model.AccountType;

public record AccountRequest(
        Long id,
        String email,
        String password,
        AccountType accountType
) {
}
