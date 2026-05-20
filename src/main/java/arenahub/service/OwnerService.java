package arenahub.service;

import arenahub.repository.OwnerRepository;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository){
        this.ownerRepository = ownerRepository;
    }
    public void findByAccountId(Long accountId) {

    }
}
