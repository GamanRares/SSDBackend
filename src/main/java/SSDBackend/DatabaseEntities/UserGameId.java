package SSDBackend.DatabaseEntities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class UserGameId implements Serializable {

    @Column(name = "user_id")
    private String userID;

    @Column(name = "game_id")
    private String gameID;

}
