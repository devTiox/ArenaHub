package arenahub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean active;

    @Email
    @Column(unique = true)
    @NotBlank
    private String email;

    @NotBlank
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @NotNull
    private AccountType type;

    public Account(String email, String passwordHash, AccountType type) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.type = type;
        this.active = true;
    }

}
