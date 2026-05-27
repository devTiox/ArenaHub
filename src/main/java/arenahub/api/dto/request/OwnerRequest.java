package arenahub.api.dto.request;

import java.util.List;

public record OwnerRequest(
        Long id,
        AccountRequest account,
        String contactEmail,
        String name,
        String phone,
        List<ArenaRequest> reservations
) {
    public OwnerRequest(Long id, AccountRequest account, String contactEmail, String name, String phone) {
        this(id, account, contactEmail, name, phone, List.of());
    }
}
