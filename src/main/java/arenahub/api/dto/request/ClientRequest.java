package arenahub.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ClientRequest {
    private Long id;
    private AccountRequest account;
    private String contactEmail;
    private String name;
    private String phone;
    private List<ReservationRequest> reservations;

    public ClientRequest(Long id, AccountRequest account, String contactEmail, String name, String phone) {
        this(id, account,contactEmail, name, phone, new ArrayList<>());
    }


}
