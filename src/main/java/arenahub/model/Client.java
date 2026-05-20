package arenahub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "account_id", nullable = false, unique = true )
    private Account account;

    @NotBlank
    private String name;

    @Email
    @Column(unique = true)
    @NotBlank
    private String contactEmail;

    @NotBlank
    private String phone;

    @OneToMany(mappedBy = "client")
    private List<Reservation> reservationList;

    public Client(Account account, String name, String contactEmail, String phone) {
        this.account = account;
        this.name = name;
        this.contactEmail = contactEmail;
        this.phone = phone;
        this.reservationList = new ArrayList<>();
    }
}
