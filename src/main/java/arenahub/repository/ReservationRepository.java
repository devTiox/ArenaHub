package arenahub.repository;

import arenahub.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    void deleteByClient_Id(Long clientId);
}
