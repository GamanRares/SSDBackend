package SSDBackend.DatabaseEntities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "Role")
public class Role {

    @NotNull
    @Id
    private String roleName;

    @OneToOne(mappedBy = "role", cascade = CascadeType.REMOVE)
    private User user;

}
