package arenahub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    private String phone;

    @OneToOne(optional = false)
    @JoinColumn(name = "account_id", nullable = false, unique = true )
    private Account account;

    @Email
    @Column(unique = true)
    @NotBlank
    private String email;

    @OneToMany(mappedBy = "owner")
    private List<Arena> arenas;

}
