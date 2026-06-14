package arenahub.owner;

import arenahub.auth.RegisterRequest;
import arenahub.jwt.CustomUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
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
    public OwnerResponse registerOwner(@Valid @RequestBody RegisterRequest owner){
        return ownerService.registerOwner(owner);
    }
}
