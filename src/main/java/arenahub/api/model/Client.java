package arenahub.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
public class Client {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    @Email
    private String email;

    @Setter
    @Getter
    private String phone;

    @Setter
    @Getter
    @OneToMany(mappedBy = "client")
    private List<Reservation> reservationList;

}
