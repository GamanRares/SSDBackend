package SSDBackend.Application;

import SSDBackend.BackingBeans.BusinessLogic.RoleEJB;
import SSDBackend.DatabaseEntities.Role;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/role")
public class RESTRole {

    @Inject
    private RoleEJB roleEJB;

    @Inject
    private Logger logger;

    //localhost:8080/SSDBackend/role/getAllRoles
    @GET
    @Path("/getAllRoles")
    public List<Role> getAllRoles() {

        logger.log(Level.INFO, "All roles successfully retrieved from database");

        return this.roleEJB.getAllRoles();

    }

    //localhost:8080/SSDBackend/role/getRoleByName?roleName=roleName
    @GET
    @Path("/getRoleByName")
    public Role getRoleByName(@QueryParam("roleName") String roleName) {

        Role retrievedRole = this.roleEJB.getRoleByName(roleName);

        if (retrievedRole != null)
            logger.log(Level.INFO, "Role " + roleName + " successfully retrieved from database");

        return retrievedRole;

    }

}