package arenahub.client;

import arenahub.account.AccountRequest;
import arenahub.reservation.ReservationRequest;

import java.util.List;

public record ClientRequest(
        Long id,
        AccountRequest account,
        String contactEmail,
        String name,
        String phone,
        List<ReservationRequest> reservations
) {
    public ClientRequest(Long id, AccountRequest account, String contactEmail, String name, String phone) {
        this(id, account, contactEmail, name, phone, List.of());
    }
}
