package arenahub.repository;

import arenahub.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Long>{
    Optional<Owner> findByAccount_Id(Long accountId);
}
