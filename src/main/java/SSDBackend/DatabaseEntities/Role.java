package SSDBackend.DatabaseEntities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(exclude = {"user"})
@ToString(exclude = {"user"})
@Entity
@Table(name = "Role")
public class Role {

    @NotNull
    @Id
    @Column(name = "Name")
    private String name;

    @JsonbTransient
    @OneToOne(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;

}
