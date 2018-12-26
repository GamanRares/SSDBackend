package SSDBackend.Application;

import SSDBackend.BackingBeans.BusinessLogic.GameEJB;
import SSDBackend.DatabaseEntities.Game;

import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

@Path("/game")
public class RESTGame {

    @Inject
    private GameEJB gameEJB;

    @GET
    @Path("/getAllGames")
    public List<Game> getAllGames() {

        return this.gameEJB.getAllGames();

    }

    @GET
    @Path("/getGameByName")
    public Game getGameByName(@QueryParam("gameName") String gameName) {

        return this.gameEJB.getGameByName(gameName);

    }

    @POST
    @Path("addGame")
    public String addGame(@QueryParam("gameName") String gameName) {

        try {

            this.gameEJB.addGame(gameName);

            return "Game Added successfully";

        } catch (EJBException e) {

            return "Game already exists";

        }

    }

}
