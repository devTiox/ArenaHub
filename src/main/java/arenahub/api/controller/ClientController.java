package arenahub.api.controller;

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

    @GetMapping("/clients")
    public List<ClientResponse> getClients(){
        return clientService.getAll();
    }

    @PostMapping("/clients")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientResponse addClient(@RequestBody ClientRequest request){
        return clientService.addClient(request);
    }

    @DeleteMapping("/clients/{clientId}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long clientId){
        clientService.deleteClient(clientId);
        return ResponseEntity.noContent().build();
    }
}
