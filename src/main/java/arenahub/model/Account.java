package arenahub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Account {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    private boolean active = true;

    @Setter
    @Getter
    @Email
    @Column(unique = true)
    @NotBlank
    private String email;

    @Setter
    @Getter
    @NotBlank
    private String passwordHash;

    @Setter
    @Getter
    @Enumerated(EnumType.STRING)
    @NotNull
    private AccountType type;

}
