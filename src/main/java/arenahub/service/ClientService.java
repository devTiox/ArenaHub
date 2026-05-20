package arenahub.service;

import arenahub.api.dto.request.ClientRequest;
import arenahub.api.dto.response.ClientResponse;
import arenahub.model.Account;
import arenahub.model.Client;
import arenahub.repository.AccountRepository;
import arenahub.repository.ClientRepository;
import arenahub.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ReservationRepository reservationRepository;
    private final AccountRepository accountRepository;
    private final AccountService accountService;

    public ClientResponse addClient(ClientRequest client) {
        Account account = accountService.createAccount(client.getAccount());
        Client savedClient = clientRepository.save(toClient(client, account));
        return ClientResponse.from(savedClient);
    }

    public List<ClientResponse> getAll() {
        return clientRepository.findAll()
                .stream()
                .map(ClientResponse::from)
                .collect(Collectors.toList());
    }

    public boolean existsById(Long clientId) {
        return clientRepository.existsById(clientId);
    }

    private Client toClient(ClientRequest request, Account account){
        return new Client(
                account,
                request.getName(),
                request.getContactEmail(),
                request.getPhone()
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
}
