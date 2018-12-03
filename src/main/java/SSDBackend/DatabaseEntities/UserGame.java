package SSDBackend.DatabaseEntities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "UserGame")
public class UserGame {

    @EmbeddedId
    private UserGameId id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userID")
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("gameID")
    private Game game;

    @Column(name = "score")
    private Long score;

}
