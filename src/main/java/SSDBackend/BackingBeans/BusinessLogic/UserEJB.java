package SSDBackend.BackingBeans.BusinessLogic;

import SSDBackend.DatabaseEntities.Role;
import SSDBackend.DatabaseEntities.User;
import SSDBackend.DatabaseEntities.User_;
import SSDBackend.Exceptions.NoSuchRoleException;
import lombok.Data;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.criteria.*;
import javax.validation.ConstraintViolationException;
import java.io.Serializable;
import java.util.List;

@Data
@Stateless
public class UserEJB implements Serializable {

    @Inject
    private BusinessLogic businessLogic;

    @Inject
    private RoleEJB roleEJB;

    public void addUser(String username, String password, Boolean active, String lastName, String firstName, String email, String roleName) throws NoSuchRoleException, ConstraintViolationException, EJBException {

        Role role = this.roleEJB.getRoleByName(roleName);

        if (role == null)
            throw new NoSuchRoleException("Incorrect roleName");

        User user = new User();
        this.setUserAttributes(user, username, password, active, lastName, firstName, email, role);

        this.businessLogic.getEm().persist(user);

    }

    public List<User> getAllUsers() {

        CriteriaBuilder builder = this.businessLogic.getEm().getCriteriaBuilder();

        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> e = query.from(User.class);

        query.select(e);

        return this.businessLogic.getEm().createQuery(query).getResultList();

    }

    public User getUserByUsername(String username) {

        CriteriaBuilder builder = this.businessLogic.getEm().getCriteriaBuilder();

        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> e = query.from(User.class);

        query.select(e)
                .where(builder.equal(e.get(User_.username), username));

        List<User> users = this.businessLogic.getEm().createQuery(query).getResultList();

        return users.isEmpty() ? null : users.get(0);

    }

    public boolean checkCredentials(String username, String password) {

        User user = this.getUserByUsername(username);

        return user != null && user.getPassword().equals(password);

    }

    public boolean isActive(String username) {

        User user = this.getUserByUsername(username);

        return user != null && user.getActive();

    }

    public boolean existsUser(String username) {

        return this.getUserByUsername(username) != null;

    }

    public boolean banOrUnbanUser(String username, Boolean active) {

        CriteriaBuilder builder = this.businessLogic.getEm().getCriteriaBuilder();

        CriteriaUpdate<User> updateQuery = builder.createCriteriaUpdate(User.class);
        Root<User> e = updateQuery.from(User.class);

        updateQuery.set(e.get(User_.active), active)
                .where(builder.equal(e.get(User_.username), username));

        return this.businessLogic.getEm().createQuery(updateQuery).executeUpdate() != 0;

    }

    private void setUserAttributes(User user, String username, String password, Boolean active, String lastName, String firstName, String email, Role role) {

        user.setUsername(username);
        user.setPassword(password);
        user.setActive(active);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setRole(role);

    }

}