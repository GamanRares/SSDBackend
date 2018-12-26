package SSDBackend.Application;

import SSDBackend.BackingBeans.BusinessLogic.RoleEJB;
import SSDBackend.DatabaseEntities.Role;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;

@Path("/role")
public class RESTRole {

    @Inject
    private RoleEJB roleEJB;

    @GET
    @Path("/getAllRoles")
    public List<Role> getAllRoles() {

        return this.roleEJB.getAllRoles();

    }

    @GET
    @Path("/getRoleByName")
    public Role getRoleByName(@QueryParam("roleName") String roleName) {

        return this.roleEJB.getRoleByName(roleName);

    }

}