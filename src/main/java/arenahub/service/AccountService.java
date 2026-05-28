package arenahub.service;

import arenahub.api.dto.request.AccountRequest;
import arenahub.api.dto.response.AccountResponse;
import arenahub.model.Account;
import arenahub.model.AccountType;
import arenahub.repository.*;
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
    private final OwnerRepository ownerRepository;
    private final ArenaService arenaService;


    public Account authAccount(AccountRequest request) {
        Account account = accountRepository.findAccountByEmail(request.email())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));

        if(passwordEncoder.matches(request.password(), account.getPasswordHash()))
            return account;
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication failed");
    }

    public Account registerAccount(AccountRequest account){
        if(accountRepository.existsByEmail(account.email())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Account email already exists");
        }

        return accountRepository.save(toAccount(account));
    }

    public AccountResponse updateAccount(AccountRequest request, Long id){
        Account account = accountRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        if(!request.password().isBlank()) {
            account.setPasswordHash(passwordEncoder.encode(request.password()));
        }
        if(!request.email().isBlank()){
            account.setEmail(request.email());
        }
        return createAccountResponse(accountRepository.save(account));
    }

    @Transactional
    public void deleteAccount(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        switch (account.getType()){
            case AccountType.CLIENT -> clientRepository.findByAccount_Id(accountId).ifPresent(client -> {
                reservationRepository.deleteByClient_Id(client.getId());
                clientRepository.delete(client);
            });
            case AccountType.ARENA_OWNER -> ownerRepository.findByAccount_Id(accountId).ifPresent(owner -> {
                arenaService.deleteByOwner(owner);
                ownerRepository.delete(owner);
            });
        }

        accountRepository.deleteById(accountId);
    }

    public List<AccountResponse> getAll() {
        return accountRepository.findAll()
                .stream()
                .map(AccountResponse::from)
                .collect(Collectors.toList());
    }

    public AccountResponse createAccountResponse(Account account) {
        return AccountResponse.from(account);
    }

    public Account toAccount(AccountRequest accountRequest){
        return new Account(
                accountRequest.email(),
                passwordEncoder.encode(accountRequest.password()),
                accountRequest.accountType()
        );
    }

}
