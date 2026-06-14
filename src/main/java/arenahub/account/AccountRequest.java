package arenahub.account;

public record AccountRequest(
        Long id,
        String email,
        String password,
        AccountType accountType
) {
}
