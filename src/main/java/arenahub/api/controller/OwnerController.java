package arenahub.api.controller;

import arenahub.api.dto.request.RegisterRequest;
import arenahub.api.dto.response.OwnerResponse;
import arenahub.service.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    @GetMapping("/owner/all")
    public List<OwnerResponse> getOwners(){
        return ownerService.getAll();
    }

    @PostMapping("/register/owner")
    @ResponseStatus(HttpStatus.CREATED)
    public OwnerResponse registerClient(@RequestBody RegisterRequest client){
        return ownerService.registerOwner(client);
    }

//    @DeleteMapping("/owner/{ownerId}")
//    public ResponseEntity<Void> deleteClient(@PathVariable Long ownerId){
//        ownerService.deleteOwner(ownerId);
//        return ResponseEntity.noContent().build();
//    }
}
