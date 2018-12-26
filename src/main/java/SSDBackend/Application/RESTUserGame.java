package SSDBackend.Application;

import SSDBackend.BackingBeans.BusinessLogic.UserGameEJB;
import SSDBackend.DatabaseEntities.UserGame;
import SSDBackend.Exceptions.NoSuchGameException;
import SSDBackend.Exceptions.NoSuchUserException;

import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;

@Path("/userGame")
public class RESTUserGame {

    @Inject
    private UserGameEJB userGameEJB;

    @GET
    @Path("/getAllUserGames")
    public List<UserGame> getAllUserGames() {

        return this.userGameEJB.getAllUserGames();

    }

    @GET
    @Path("/getUserGame")
    public UserGame getUserGame(@QueryParam("username") String username, @QueryParam("gameName") String gameName) {

        return this.userGameEJB.getUserGame(username, gameName);

    }

    @POST
    @Path("/addUserGame")
    public String addUserGame(@QueryParam("username") String username, @QueryParam("gameName") String gameName, @QueryParam("score") Long score) {

        try {

            this.userGameEJB.addUserGame(username, gameName, score);

            return "UserGame added successfully";

        } catch (NoSuchUserException | NoSuchGameException e) {

            return e.getMessage();

        } catch (EJBException e) {
            return "UserGame already exists";
        }

    }

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
