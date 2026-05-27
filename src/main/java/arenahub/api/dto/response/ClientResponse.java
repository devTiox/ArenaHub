package arenahub.api.dto.response;

import arenahub.model.AccountType;
import arenahub.model.Client;

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
