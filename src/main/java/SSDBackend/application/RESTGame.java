package SSDBackend.application;

import SSDBackend.BackingBeans.businessLogic.GameEJB;
import SSDBackend.databaseEntities.Game;
import SSDBackend.message.Message;

import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/game")
public class RESTGame {

    @Inject
    private GameEJB gameEJB;

    @Inject
    private Logger logger;

    //localhost:8080/SSDBackend/game/getAllGames
    @GET
    @Path("/getAllGames")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Game> getAllGames() {

        this.logger.log(Level.INFO, "All games retrieved successfully from database");

        return this.gameEJB.getAllGames();

    }

    //localhost:8080/SSDBackend/game/getGameByName?gameName=gameName
    @GET
    @Path("/getGameByName")
    @Produces(MediaType.APPLICATION_JSON)
    public Game getGameByName(@QueryParam("gameName") String gameName) {

        Game retrievedGame = this.gameEJB.getGameByName(gameName);

        if (retrievedGame != null)
            this.logger.log(Level.INFO, "Game " + gameName + " successfully retrieved from database");

        return retrievedGame;

    }

    //localhost:8080/SSDBackend/game/addGame?gameName=gameName
    @GET
    @Path("/addGame")
    @Produces(MediaType.APPLICATION_JSON)
    public Message addGame(@QueryParam("gameName") String gameName) {

        try {

            this.gameEJB.addGame(gameName);

            this.logger.log(Level.INFO, "Game " + gameName + "successfully added to database");

            return new Message("Game Added successfully");

        } catch (EJBException e) {

            this.logger.log(Level.SEVERE, "Game " + gameName + " already exists in database");

            return new Message("Game already exists");

        }

    }

    //localhost:8080/SSDBackend/game/deleteGame?gameName=gameName
    @GET
    @Path("/deleteGame")
    @Produces(MediaType.APPLICATION_JSON)
    public Message deleteGame(@QueryParam("gameName") String gameName) {

        try {

            this.logger.log(Level.INFO, "Game " + gameName + "successfully deleted from database");

            this.gameEJB.deleteGame(gameName);

            return new Message("Game Deleted successfully");

        } catch (EJBException e) {

            this.logger.log(Level.SEVERE, "Game " + gameName + " couldn't be deleted from database, it might not exists in the database");

            return new Message("Game Deleted successfully");

        }

    }

}
