package SSDBackend.DatabaseEntities;

import SSDBackend.Validators.EmailValidation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(exclude = {"userGameList", "role"})
@ToString(exclude = "role")
@Entity
@Table(name = "User")
public class User {

    @NotNull
    @Id
    @Column(name = "Username")
    private String username;

    @NotNull
    @Column(name = "Password")
    private String password;

    @NotNull
    @Column(name = "FirstName")
    private String firstName;

    @NotNull
    @Column(name = "LastName")
    private String lastName;

    @NotNull
    @EmailValidation
    @Column(name = "Email")
    private String email;

    @NotNull
    @Column(name = "Active")
    private Boolean active;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserGame> userGameList = new ArrayList<>();

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Role")
    private Role role;

}
