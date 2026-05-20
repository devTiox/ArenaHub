package arenahub.api.dto.response;

import arenahub.model.AccountType;
import arenahub.model.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ClientResponse {
    private Long id;
    private Long accountId;
    private String email;
    private AccountType accountType;
    private String contactEmail;
    private String name;
    private String phone;

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
