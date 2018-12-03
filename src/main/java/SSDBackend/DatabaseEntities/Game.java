package SSDBackend.DatabaseEntities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Game")
public class Game {

    @NotNull
    @Id
    private String name;

    @OneToMany(mappedBy = "game")
    private List<UserGame> userGameList = new ArrayList<>();

}
