package SSDBackend.BackingBeans.BusinessLogic;

import SSDBackend.DatabaseEntities.*;
import SSDBackend.Exceptions.NoSuchGameException;
import SSDBackend.Exceptions.NoSuchUserException;
import lombok.Data;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

@Data
@Stateless
public class UserGameEJB implements Serializable {

    @Inject
    private BusinessLogic businessLogic;

    @Inject
    private UserEJB userEJB;

    @Inject
    private GameEJB gameEJB;

    public void addUserGame(String username, String gameName, Long score) throws NoSuchUserException, NoSuchGameException {

        UserGame userGame = new UserGame();

        User user = this.userEJB.getUserByUsername(username);
        Game game = this.gameEJB.getGameByName(gameName);

        if (user == null)
            throw new NoSuchUserException("Username incorrect");

        if (game == null)
            throw new NoSuchGameException("Game name incorrect");

        this.setUserGameAttributes(userGame, user, game, score);

        this.businessLogic.getEm().persist(userGame);


    }

    public List<UserGame> getAllUserGames() {

        CriteriaBuilder builder = this.businessLogic.getEm().getCriteriaBuilder();

        CriteriaQuery<UserGame> query = builder.createQuery(UserGame.class);
        Root<UserGame> e = query.from(UserGame.class);

        query.select(e);

        return this.businessLogic.getEm().createQuery(query).getResultList();

    }

    public UserGame getUserGame(String username, String gameName) {

        CriteriaBuilder builder = this.businessLogic.getEm().getCriteriaBuilder();

        CriteriaQuery<UserGame> query = builder.createQuery(UserGame.class);
        Root<UserGame> e = query.from(UserGame.class);

        query.select(e)
                .where(builder.and(builder.equal(e.get(UserGame_.id).get(UserGameId_.userID), username), builder.equal(e.get(UserGame_.id).get(UserGameId_.gameID), gameName)));

        List<UserGame> userGames = this.businessLogic.getEm().createQuery(query).getResultList();

        return userGames.isEmpty() ? null : userGames.get(0);

    }

    public void updateScore(String username, String gameName, Long score) {

        UserGame userGame = this.getUserGame(username, gameName);

        userGame.setScore(score);

    }

    private void setUserGameAttributes(UserGame userGame, User user, Game game, Long score) {

        UserGameId userGameId = new UserGameId();
        userGameId.setUserID(user.getUsername());
        userGameId.setGameID(game.getName());

        userGame.setUser(user);
        userGame.setGame(game);
        userGame.setScore(score);

    }

}
