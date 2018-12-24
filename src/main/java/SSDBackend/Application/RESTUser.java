package SSDBackend.Application;

import SSDBackend.BackingBeans.BusinessLogic.UserEJB;
import SSDBackend.DatabaseEntities.User;
import SSDBackend.Exceptions.NotSuchRoleException;

import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.transaction.RollbackException;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.*;
import java.util.List;

@Path("/user")
public class RESTUser {

    @Inject
    private UserEJB userEJB;

    @GET
    @Path("getAllUsers")
    public List<User> getAllUsers() {

        return this.userEJB.getAllUsers();

    }

    @GET
    @Path("getUserByUsername/{username}")
    public User getUserByUsername(@PathParam("username") String username) {

        return this.userEJB.getUserByUsername(username);

    }

    @GET
    @Path("existsUser/{username}")
    public boolean existsUser(@PathParam("username") String username) {

        return this.userEJB.existsUser(username);

    }

    @GET
    @Path("isActive/{username}")
    public boolean isActive(@PathParam("username") String username) {

        return this.userEJB.isActive(username);

    }

    @POST
    @Path("unbanUser/{username}")
    public boolean unbanUser(@PathParam("username") String username) {

        return this.userEJB.banOrUnbanUser(username, Boolean.TRUE);

    }

    @POST
    @Path("checkCredentials")
    public boolean checkCredentials(@QueryParam("username") String username, @QueryParam("password") String password) {

        return this.userEJB.checkCredentials(username, password);

    }

    @POST
    @Path("addUser")
    public String addUser(@QueryParam("username") String username, @QueryParam("password") String password, @QueryParam("active") Boolean active, @QueryParam("firstName") String firstName, @QueryParam("lastName") String lastName, @QueryParam("email") String email, @QueryParam("roleName") String roleName) {

        try {

            this.userEJB.addUser(username, password, active, lastName, firstName, email, roleName);

            return "User Added successfully !";

        } catch (NotSuchRoleException e) {

            return e.getMessage();

        } catch (EJBException e) {

            if (e.getCausedByException() instanceof RollbackException)
                return "User already exists !";
            else {

                StringBuilder stringBuilder = new StringBuilder();
                String newLine = System.getProperty("line.separator");

                ConstraintViolationException constraintViolation = (ConstraintViolationException) e.getCausedByException();
                constraintViolation.getConstraintViolations().forEach(err -> stringBuilder.append(err.getMessage()).append(newLine));

                return stringBuilder.toString();
            }
        }

    }

    @DELETE
    @Path("banUser/{username}")
    public boolean banUser(@PathParam("username") String username) {

        return this.userEJB.banOrUnbanUser(username, Boolean.FALSE);

    }

}