package arenahub.client;

import arenahub.auth.RegisterRequest;
import arenahub.jwt.CustomUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/client/all")
    public List<ClientResponse> getClients(){
        return clientService.getAll();
    }

    @GetMapping("/client/me")
    public ClientResponse getClient(@AuthenticationPrincipal CustomUserDetails user){
        return clientService.getClientByAccount_Id(user.getId());
    }

    @PostMapping("/register/client")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientResponse registerClient(@Valid @RequestBody RegisterRequest client){
        return clientService.registerClient(client);
    }
}
