package arenahub.client;

import arenahub.account.Account;
import arenahub.account.AccountRepository;
import arenahub.account.AccountRequest;
import arenahub.account.AccountService;
import arenahub.account.AccountType;

import arenahub.auth.RegisterRequest;
import arenahub.reservation.ReservationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ReservationRepository reservationRepository;
    private final AccountRepository accountRepository;
    private final AccountService accountService;

    public Client findByAccount(AccountRequest account) {
        Account authenticatedAccount = accountService.authAccount(account);

        if (!authenticatedAccount.isActive()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Account is inactive");
        }

        return clientRepository.findByAccount_Id(authenticatedAccount.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));
    }

    public List<ClientResponse> getAll() {
        return clientRepository.findAll()
                .stream()
                .map(ClientResponse::from)
                .toList();
    }

    public ClientResponse registerClient(RegisterRequest request) {
        AccountRequest accountRequest = new AccountRequest(null, request.email(), request.password(), AccountType.CLIENT);
        ClientRequest clientRequest = new ClientRequest(null, null, request.contactEmail(), request.name(), request.phone());
        Account account = accountService.registerAccount(accountRequest);
        return ClientResponse.from(clientRepository.save(toClient(clientRequest, account)));
    }

    public boolean existsById(Long clientId) {
        return clientRepository.existsById(clientId);
    }

    private Client toClient(ClientRequest request, Account account){
        return new Client(
                account,
                request.name(),
                request.contactEmail(),
                request.phone()
        );
    }

    @Transactional
    public void deleteClient(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));

        reservationRepository.deleteByClient_Id(clientId);
        clientRepository.delete(client);
        accountRepository.delete(client.getAccount());
    }

    @Transactional
    public void deleteClientByAccountId(Long accountId) {
        clientRepository.findByAccount_Id(accountId)
                .ifPresentOrElse(
                        client -> deleteClient(client.getId()),
                        () -> {
                            if (!accountRepository.existsById(accountId)) {
                                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
                            }

                            accountRepository.deleteById(accountId);
                        }
                );
    }

    public ClientResponse getClientByAccount_Id(Long accountId) {
        return ClientResponse.from(clientRepository.findByAccount_Id(accountId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Client not found")));
    }
}
