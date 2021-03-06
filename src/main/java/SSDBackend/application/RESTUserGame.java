package SSDBackend.application;

import SSDBackend.BackingBeans.businessLogic.UserGameEJB;
import SSDBackend.databaseEntities.UserGame;
import SSDBackend.exceptions.NoSuchGameException;
import SSDBackend.exceptions.NoSuchOrderException;
import SSDBackend.exceptions.NoSuchUserException;
import SSDBackend.message.Message;

import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserGame> getAllUserGames() {

        this.logger.log(Level.INFO, "All UserGames retrieved successfully from database.");

        return this.userGameEJB.getAllUserGames();

    }

    //localhost:8080/SSDBackend/userGame/getAllUserGamesOrdered?gameNameOrder=gameNameOrder&gameScoreOrder=gameScoreOrder
    @GET
    @Path("/getAllUserGamesOrdered")
    @Produces(MediaType.APPLICATION_JSON)
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
    @Produces(MediaType.APPLICATION_JSON)
    public UserGame getUserGame(@QueryParam("username") String username, @QueryParam("gameName") String gameName) {

        UserGame retrievedUserGame = this.userGameEJB.getUserGame(username, gameName);

        if (retrievedUserGame != null)
            this.logger.log(Level.INFO, "UserGame " + username + " " + gameName + " retrieved successfully from database.");

        return retrievedUserGame;

    }

    //localhost:8080/SSDBackend/userGame/addUserGame?username=username&gameName=gameName&score=score
    @GET
    @Path("/addUserGame")
    @Produces(MediaType.APPLICATION_JSON)
    public Message addUserGame(@QueryParam("username") String username, @QueryParam("gameName") String gameName, @QueryParam("score") Long score) {

        try {

            this.userGameEJB.addUserGame(username, gameName, score);

            this.logger.log(Level.INFO, "UserGame " + username + " " + gameName + " added successfully to database.");

            return new Message("UserGame added successfully");

        } catch (NoSuchUserException | NoSuchGameException e) {

            this.logger.log(Level.WARNING, e.getMessage());

            return new Message(e.getMessage());

        } catch (EJBException e) {

            this.logger.log(Level.SEVERE, "UserGame " + username + " " + gameName + " already exists in the database.");

            return new Message("UserGame already exists");
        }

    }

    //localhost:8080/SSDBackend/userGame/updateScore?username=username&gameName=gameName&score=score
    @GET
    @Path("/updateScore")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean updateScore(@QueryParam("username") String username, @QueryParam("gameName") String gameName, @QueryParam("score") Long score) {

        try {

            this.userGameEJB.updateScore(username, gameName, score);

            return true;

        } catch (EJBException e) {
            return false;
        }

    }

}
