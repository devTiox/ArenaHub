package arenahub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String phone;

    @OneToOne(optional = false)
    @JoinColumn(name = "account_id", nullable = false, unique = true )
    private Account account;

    @Email
    @Column(unique = true)
    @NotBlank
    private String contactEmail;

    @OneToMany(mappedBy = "owner")
    private List<Arena> arenas;

}
