package SSDBackend.DatabaseEntities;

import SSDBackend.Validators.EmailValidation;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "User")
public class User {

    @NotNull
    @Id
    private String username;

    @NotNull
    @Column(name = "First_Name")
    private String firstName;

    @NotNull
    @Column(name = "Last_Name")
    private String lastName;

    @NotNull
    @EmailValidation
    @Column(name = "Email")
    private String email;

    @OneToMany(mappedBy = "user")
    private List<UserGame> userGameList = new ArrayList<>();

    @NotNull
    @OneToOne
    @JoinColumn(name = "Role")
    private Role role;

}
