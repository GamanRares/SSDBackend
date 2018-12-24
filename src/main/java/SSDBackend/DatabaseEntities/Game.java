package SSDBackend.DatabaseEntities;

import lombok.Data;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Game")
public class Game {

    @NotNull
    @Id
    @Column(name = "Name")
    private String name;

    @JsonbTransient
    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY)
    private List<UserGame> userGameList = new ArrayList<>();

}
