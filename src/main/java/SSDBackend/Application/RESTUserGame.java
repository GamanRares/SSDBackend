package SSDBackend.Application;

import SSDBackend.BackingBeans.BusinessLogic.UserGameEJB;
import SSDBackend.DatabaseEntities.UserGame;
import SSDBackend.Exceptions.NoSuchGameException;
import SSDBackend.Exceptions.NoSuchOrderException;
import SSDBackend.Exceptions.NoSuchUserException;

import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/userGame")
public class RESTUserGame {

    @Inject
    private UserGameEJB userGameEJB;

    @Inject
    private Logger logger;

    //localhost:8080/SSDBackend/userGame/getAllUserGames
    @GET
    @Path("/getAllUserGames")
    public List<UserGame> getAllUserGames() {

        this.logger.log(Level.INFO, "All UserGames retrieved successfully from database.");

        return this.userGameEJB.getAllUserGames();

    }

    //localhost:8080/SSDBackend/userGame/getAllUserGamesOrdered?gameNameOrder=gameNameOrder&gameScoreOrder=gameScoreOrder
    @GET
    @Path("/getAllUserGamesOrdered")
    public List<UserGame> getAllUserGamesOrdered(@QueryParam("gameNameOrder") String gameNameOrder, @QueryParam("gameScoreOrder") String gameScoreOrder) {

        try {

            List<UserGame> retrievedUserGames = this.userGameEJB.getAllUserGamesOrdered(gameNameOrder, gameScoreOrder);

            if (retrievedUserGames != null)
                this.logger.log(Level.INFO, "AllUserGames retrieved successfully from database.");

            return retrievedUserGames;

        } catch (NoSuchOrderException e) {

            return null;

        }

    }

    //localhost:8080/SSDBackend/userGame/getUserGame?username=username&gameName=gameName
    @GET
    @Path("/getUserGame")
    public UserGame getUserGame(@QueryParam("username") String username, @QueryParam("gameName") String gameName) {

        UserGame retrievedUserGame = this.userGameEJB.getUserGame(username, gameName);

        if (retrievedUserGame != null)
            this.logger.log(Level.INFO, "UserGame " + username + " " + gameName + " retrieved successfully from database.");

        return retrievedUserGame;

    }

    //localhost:8080/SSDBackend/userGame/addUserGame?username=username&gameName=gameName&score=score
    @GET
    @Path("/addUserGame")
    public String addUserGame(@QueryParam("username") String username, @QueryParam("gameName") String gameName, @QueryParam("score") Long score) {

        try {

            this.userGameEJB.addUserGame(username, gameName, score);

            this.logger.log(Level.INFO, "UserGame " + username + " " + gameName + " added successfully to database.");

            return "UserGame added successfully";

        } catch (NoSuchUserException | NoSuchGameException e) {

            this.logger.log(Level.WARNING, e.getMessage());

            return e.getMessage();

        } catch (EJBException e) {

            this.logger.log(Level.SEVERE, "UserGame " + username + " " + gameName + " already exists in the database.");

            return "UserGame already exists";
        }

    }

    //localhost:8080/SSDBackend/userGame/updateScore?username=username&gameName=gameName&score=score
    @POST
    @Path("/updateScore")
    public boolean updateScore(@QueryParam("username") String username, @QueryParam("gameName") String gameName, @QueryParam("score") Long score) {

        try {

            this.userGameEJB.updateScore(username, gameName, score);

            return true;

        } catch (EJBException e) {
            return false;
        }

    }

}
