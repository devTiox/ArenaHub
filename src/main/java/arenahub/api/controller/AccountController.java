package arenahub.api.controller;

import arenahub.api.dto.request.AccountRequest;
import arenahub.api.dto.response.AccountResponse;
import arenahub.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/account")
    public List<AccountResponse> getAccounts() {
        return accountService.getAll();
    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponse registerAccount(@RequestBody AccountRequest request){
        return AccountResponse.from(accountService.registerAccount(request));
    }

    @DeleteMapping("/account/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long accountId){
        accountService.deleteAccount(accountId);
        return ResponseEntity.noContent().build();
    }
}
