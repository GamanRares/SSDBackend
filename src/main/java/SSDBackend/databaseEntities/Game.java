package SSDBackend.databaseEntities;

import lombok.Data;

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

    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY)
    private List<UserGame> userGameList = new ArrayList<>();

}
