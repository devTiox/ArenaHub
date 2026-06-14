package arenahub.account;

import arenahub.jwt.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/account/all")
    public List<AccountResponse> getAccounts() {
        return accountService.getAll();
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteAccount(@AuthenticationPrincipal CustomUserDetails user){
        accountService.deleteAccount(user.getId());
        return ResponseEntity.noContent().build();
    }
}
