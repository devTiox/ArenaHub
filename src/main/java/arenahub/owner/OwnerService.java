package arenahub.owner;

import arenahub.account.AccountRequest;
import arenahub.auth.RegisterRequest;
import arenahub.account.Account;
import arenahub.account.AccountType;
import arenahub.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final AccountService accountService;

    public List<OwnerResponse> getAll() {
        return ownerRepository.findAll()
                .stream()
                .map(OwnerResponse::from)
                .toList();
    }

    public OwnerResponse registerOwner(RegisterRequest request) {
        AccountRequest accountRequest = new AccountRequest(null, request.email(), request.password(), AccountType.ARENA_OWNER);
        OwnerRequest clientRequest = new OwnerRequest(null, null, request.contactEmail(), request.name(), request.phone());
        Account account = accountService.registerAccount(accountRequest);
        return OwnerResponse.from(ownerRepository.save(toOwner(clientRequest, account)));
    }

    public Owner toOwner(OwnerRequest owner, Account account){
        return new Owner(
                owner.id(),
                owner.name(),
                owner.phone(),
                account,
                owner.contactEmail(),
                List.of()
        );
    }

    public OwnerResponse getOwnerByAccount_Id(Long id) {
        return OwnerResponse.from(ownerRepository.findByAccount_Id(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Owner not found")));
    }
}
