package arenahub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_account_id", nullable = false)
    private Account ownerAccount;

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
    @NotBlank
    private String city;

    @Setter
    @Getter
    @NotBlank
    private String address;

    @Setter
    @Getter
    @NotBlank
    private String openHours;

    @Setter
    @Getter
    @OneToMany(mappedBy = "arena")
    private List<Reservation> reservationList;

}
