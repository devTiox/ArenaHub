package arenahub.api.controller;

import arenahub.api.dto.request.AccountRequest;
import arenahub.api.dto.request.ClientRequest;
import arenahub.api.dto.response.ClientResponse;
import arenahub.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/client/all")
    public List<ClientResponse> getClients(){
        return clientService.getAll();
    }

    @PostMapping("/login/client")
    public ClientResponse loginClient(@RequestBody AccountRequest account){
        return ClientResponse.from(clientService.findByAccount(account));
    }

    @PostMapping("/register/client")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientResponse registerClient(@RequestBody ClientRequest client){
        return clientService.registerClient(client, client.getAccount());
    }

    @DeleteMapping("/client/{clientId}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long clientId){
        clientService.deleteClient(clientId);
        return ResponseEntity.noContent().build();
    }
}
