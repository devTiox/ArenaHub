package arenahub.api.controller;

import arenahub.api.dto.request.RegisterRequest;
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

    @PostMapping("/register/client")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientResponse registerClient(@RequestBody RegisterRequest client){
        return clientService.registerClient(client);
    }

    @DeleteMapping("/client/{clientId}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long clientId){
        clientService.deleteClient(clientId);
        return ResponseEntity.noContent().build();
    }
}
