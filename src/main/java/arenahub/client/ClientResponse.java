package arenahub.client;

import arenahub.account.AccountType;

public record ClientResponse(
        Long id,
        Long accountId,
        String email,
        AccountType accountType,
        String contactEmail,
        String name,
        String phone
) {
    public static ClientResponse from(Client client) {
        return new ClientResponse(
                client.getId(),
                client.getAccount().getId(),
                client.getAccount().getEmail(),
                client.getAccount().getType(),
                client.getContactEmail(),
                client.getName(),
                client.getPhone()
        );
    }
}
