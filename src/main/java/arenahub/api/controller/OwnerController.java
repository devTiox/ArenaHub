package arenahub.api.controller;

import arenahub.api.dto.request.RegisterRequest;
import arenahub.api.dto.response.OwnerResponse;
import arenahub.model.CustomUserDetails;
import arenahub.service.OwnerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @GetMapping("/owner/me")
    public OwnerResponse getOwner(@AuthenticationPrincipal CustomUserDetails user){
        return ownerService.getOwnerByAccount_Id(user.getId());
    }

    @PostMapping("/register/owner")
    @ResponseStatus(HttpStatus.CREATED)
    public OwnerResponse registerClient(@Valid @RequestBody RegisterRequest client){
        return ownerService.registerOwner(client);
    }
}
