package SSDBackend.application;

import SSDBackend.BackingBeans.businessLogic.UserEJB;
import SSDBackend.databaseEntities.User;
import SSDBackend.exceptions.NoSuchRoleException;
import SSDBackend.message.Message;

import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.transaction.RollbackException;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/user")
public class RESTUser {

    @Inject
    private UserEJB userEJB;

    @Inject
    private Logger logger;

    //localhost:8080/SSDBackend/user/getAllUsers
    @GET
    @Path("/getAllUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() {

        logger.log(Level.INFO, "All users successfully retrieved from database");

        return this.userEJB.getAllUsers();

    }

    //localhost:8080/SSDBackend/user/getUserByUsername?username=username
    @GET
    @Path("/getUserByUsername")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserByUsername(@QueryParam("username") String username) {

        User retrievedUser = this.userEJB.getUserByUsername(username);

        if (retrievedUser != null)
            logger.log(Level.INFO, "User " + username + " successfully retrieved from database");

        return retrievedUser;

    }

    //localhost:8080/SSDBackend/user/existsUser?username=username
    @GET
    @Path("/existsUser")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean existsUser(@QueryParam("username") String username) {

        return this.userEJB.existsUser(username);

    }

    //localhost:8080/SSDBackend/user/isActive?username=username
    @GET
    @Path("/isActive")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean isActive(@QueryParam("username") String username) {

        return this.userEJB.isActive(username);

    }

    //localhost:8080/SSDBackend/user/unbanUser?username=username
    @GET
    @Path("/unbanUser")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean unbanUser(@QueryParam("username") String username) {

        return this.userEJB.banOrUnbanUser(username, Boolean.TRUE);

    }

    //localhost:8080/SSDBackend/user/checkCredentials?username=username&password=password
    @GET
    @Path("/checkCredentials")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean checkCredentials(@QueryParam("username") String username, @QueryParam("password") String password) {

        return this.userEJB.checkCredentials(username, password);

    }

    //localhost:8080/SSDBackend/user/addUser?username=username&password=password&active=active&firstName=firstName&lastName=lastName&email=email&roleName=roleName
    @GET
    @Path("/addUser")
    @Produces(MediaType.APPLICATION_JSON)
    public Message addUser(@QueryParam("username") String username, @QueryParam("password") String password, @QueryParam("active") Boolean active, @QueryParam("firstName") String firstName, @QueryParam("lastName") String lastName, @QueryParam("email") String email, @QueryParam("roleName") String roleName) {

        try {

            this.userEJB.addUser(username, password, active, lastName, firstName, email, roleName);

            this.logger.log(Level.INFO, "User " + username + " added successfully to the database");

            return new Message("User Added successfully !");

        } catch (NoSuchRoleException e) {

            this.logger.log(Level.WARNING, "Role " + roleName + " is not correct");

            return new Message(e.getMessage());

        } catch (EJBException e) {

            if (e.getCausedByException() instanceof RollbackException) {

                this.logger.log(Level.SEVERE, "User " + username + " already exists in the database");

                return new Message("User already exists !");

            }

            else {

                StringBuilder stringBuilder = new StringBuilder();
                String newLine = System.getProperty("line.separator");

                ConstraintViolationException constraintViolation = (ConstraintViolationException) e.getCausedByException();
                constraintViolation.getConstraintViolations().forEach(err -> stringBuilder.append(err.getMessage()).append(newLine));

                this.logger.log(Level.WARNING, "User " + username + "couldn't be added to the database :" + stringBuilder.toString());

                return new Message(stringBuilder.toString());
            }
        }

    }

    //localhost:8080/SSDBackend/user/banUser?username=username
    @GET
    @Path("/banUser")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean banUser(@QueryParam("username") String username) {

        return this.userEJB.banOrUnbanUser(username, Boolean.FALSE);

    }

}