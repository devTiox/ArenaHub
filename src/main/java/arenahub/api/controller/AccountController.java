package arenahub.api.controller;

import arenahub.api.dto.request.LoginRequest;
import arenahub.api.dto.response.AccountResponse;
import arenahub.api.dto.response.AuthResponse;
import arenahub.config.JwtService;
import arenahub.model.CustomUserDetails;
import arenahub.service.AccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class AccountController {

    private final AuthenticationManager authenticationManager;
    private final AccountService accountService;
    private final JwtService jwtService;

    @GetMapping("/account/all")
    public List<AccountResponse> getAccounts() {
        return accountService.getAll();
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(customUserDetails);

        return new AuthResponse(token);
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteAccount(@AuthenticationPrincipal CustomUserDetails user){
        accountService.deleteAccount(user.getId());
        return ResponseEntity.noContent().build();
    }
}
