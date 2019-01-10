package SSDBackend.databaseEntities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(exclude = {"user"})
@Entity
@Table(name = "UserGame")
public class UserGame {

    @EmbeddedId
    private UserGameId id;

    @NotNull
    @JsonbTransient
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("userID")
    private User user;

    @NotNull
    @JsonbTransient
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("gameID")
    private Game game;

    @Column(name = "Score")
    private Long score;

}
