package arenahub.repository;

import arenahub.model.Arena;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArenaRepository extends JpaRepository<Arena, Long> {
}
