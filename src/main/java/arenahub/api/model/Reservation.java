package arenahub.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
public class Reservation {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "arena_id", nullable = false)
    private Arena arena;

    @Setter
    @Getter
    private LocalDateTime reservationTime;
}
