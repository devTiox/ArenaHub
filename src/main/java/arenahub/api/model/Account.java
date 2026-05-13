package arenahub.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
    private boolean active;

    @Setter
    @Getter
    @Email
    @Column(unique = true)
    private String email;

    @Setter
    @Getter
    @Column(unique = true)
    private String name;

    @Setter
    @Getter
    private String passwordHash;

    @Setter
    @Getter
    @Enumerated(EnumType.STRING)
    private AccountType type;


}
