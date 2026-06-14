package arenahub.account;

public record AccountResponse(
        Long id,
        String email,
        AccountType accountType,
        boolean active
) {
    public static AccountResponse from(Account account) {
        return new AccountResponse(
                account.getId(),
                account.getEmail(),
                account.getType(),
                account.isActive()
        );
    }
}
