package SSDBackend.BackingBeans.businessLogic;

import SSDBackend.databaseEntities.Role;
import SSDBackend.databaseEntities.Role_;
import lombok.Data;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Data
@Stateless
public class RoleEJB {

    @Inject
    private BusinessLogic businessLogic;

    public Role getRoleByName(String roleName) {

        CriteriaBuilder builder = this.businessLogic.getEm().getCriteriaBuilder();

        CriteriaQuery<Role> query = builder.createQuery(Role.class);
        Root<Role> e = query.from(Role.class);

        query.select(e)
                .where(builder.equal(e.get(Role_.name), roleName));

        List<Role> roles = this.businessLogic.getEm().createQuery(query).getResultList();

        return roles.isEmpty() ? null : roles.get(0);

    }

    public List<Role> getAllRoles() {

        CriteriaBuilder builder = this.businessLogic.getEm().getCriteriaBuilder();

        CriteriaQuery<Role> query = builder.createQuery(Role.class);
        Root<Role> e = query.from(Role.class);

        query.select(e);

        return this.businessLogic.getEm().createQuery(query).getResultList();

    }

}
