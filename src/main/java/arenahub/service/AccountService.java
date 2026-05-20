package arenahub.service;

import arenahub.api.dto.request.AccountRequest;
import arenahub.api.dto.response.AccountResponse;
import arenahub.model.Account;
import arenahub.repository.AccountRepository;
import arenahub.repository.ClientRepository;
import arenahub.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;
    private final ReservationRepository reservationRepository;
    private final PasswordEncoder passwordEncoder;


    public Account createAccount(AccountRequest account){
        if(accountRepository.existsByEmail(account.getEmail())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Account email already exists");
        }

        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(toAccount(account));
    }

    public AccountResponse createAccountResponse(AccountRequest account) {
        return AccountResponse.from(createAccount(account));
    }

    public void updateAccount(AccountRequest request, Long id){
        Account account = accountRepository.findById(id).orElseThrow();
        if(!request.getPassword().isBlank()) {
            account.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        }
        if(!request.getEmail().isBlank()){
            account.setEmail(request.getEmail());
        }
        accountRepository.save(account);
    }

    private Account toAccount(AccountRequest accountRequest){
        return new Account(
                accountRequest.getEmail(),
                accountRequest.getPassword(),
                accountRequest.getAccountType()
        );
    }

    @Transactional
    public void deleteAccount(Long accountId) {
        if(!accountRepository.existsById(accountId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        }

        clientRepository.findByAccount_Id(accountId).ifPresent(client -> {
            reservationRepository.deleteByClient_Id(client.getId());
            clientRepository.delete(client);
        });
        accountRepository.deleteById(accountId);
    }


    public List<AccountResponse> getAll() {
        return accountRepository.findAll()
                .stream()
                .map(AccountResponse::from)
                .collect(Collectors.toList());
    }
}
