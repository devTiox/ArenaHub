package arenahub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Entity
@Getter
@Setter
public class Arena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String contactEmail;

    @NotBlank
    private String phone;

    @NotBlank
    private String city;

    @NotBlank
    private String address;

    @NotBlank
    private String openHours;

    @OneToMany(mappedBy = "arena")
    private List<Reservation> reservationList;

}
