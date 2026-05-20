package arenahub.repository;

import arenahub.model.Arena;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArenaRepository extends JpaRepository<Arena, Long> {
    List<Arena> findByOwner_Account_Id(Long accountId);
}
