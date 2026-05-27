package arenahub.service;

import arenahub.api.dto.request.AccountRequest;
import arenahub.api.dto.request.OwnerRequest;
import arenahub.api.dto.request.RegisterRequest;
import arenahub.api.dto.response.OwnerResponse;
import arenahub.model.Account;
import arenahub.model.AccountType;
import arenahub.model.Owner;
import arenahub.repository.OwnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final AccountService accountService;

    public List<OwnerResponse> getAll() {
        return ownerRepository.findAll()
                .stream()
                .map(OwnerResponse::from)
                .collect(Collectors.toList());
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
}
