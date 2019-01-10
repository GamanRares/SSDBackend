package SSDBackend.BackingBeans.businessLogic;

import SSDBackend.databaseEntities.Game;
import SSDBackend.databaseEntities.Game_;
import lombok.Data;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

@Data
@Stateless
public class GameEJB implements Serializable {

    @Inject
    private BusinessLogic businessLogic;

    public void addGame(String gameName) throws EJBException {

        Game game = new Game();
        game.setName(gameName);

        this.businessLogic.getEm().persist(game);

    }

    public List<Game> getAllGames() {

        CriteriaBuilder builder = this.businessLogic.getEm().getCriteriaBuilder();

        CriteriaQuery<Game> query = builder.createQuery(Game.class);
        Root<Game> e = query.from(Game.class);

        query.select(e);

        return this.businessLogic.getEm().createQuery(query).getResultList();

    }

    public Game getGameByName(String gameName) {

        CriteriaBuilder builder = this.businessLogic.getEm().getCriteriaBuilder();

        CriteriaQuery<Game> query = builder.createQuery(Game.class);
        Root<Game> e = query.from(Game.class);

        query.select(e)
                .where(builder.equal(e.get(Game_.name), gameName));

        List<Game> games = this.businessLogic.getEm().createQuery(query).getResultList();

        return games.isEmpty() ? null : games.get(0);

    }

    public void deleteGame(String gameName) {

        CriteriaBuilder builder = this.businessLogic.getEm().getCriteriaBuilder();

        CriteriaDelete<Game> query = builder.createCriteriaDelete(Game.class);
        Root<Game> e = query.from(Game.class);

        query.where(builder.equal(e.get(Game_.name), gameName));

        this.businessLogic.getEm().createQuery(query).executeUpdate();

    }

}
