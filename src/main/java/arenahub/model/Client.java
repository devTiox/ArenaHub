package arenahub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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

    @Getter
    @Setter
    @OneToOne(optional = false)
    @JoinColumn(name = "account_id", nullable = false, unique = true )
    private Account account;

    @Setter
    @Getter
    @NotBlank
    private String name;

    @Setter
    @Getter
    @Email
    @NotBlank
    private String contactEmail;

    @Setter
    @Getter
    @NotBlank
    private String phone;

    @Setter
    @Getter
    @OneToMany(mappedBy = "client")
    private List<Reservation> reservationList;

}
