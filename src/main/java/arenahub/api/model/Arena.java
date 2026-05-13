package arenahub.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
public class Arena {

    @Setter
    @Getter
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
    private String city;

    @Setter
    @Getter
    private String address;

    @Setter
    @Getter
    private String openHours;

    @Setter
    @Getter
    @OneToMany(mappedBy = "arena")
    private List<Reservation> reservationList;

}
