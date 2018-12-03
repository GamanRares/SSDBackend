package SSDBackend.BackingBeans.BusinessLogic;

import lombok.Data;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Data
@Stateless
public class RoleEJB {

    @Inject
    private BusinessLogic businessLogic;

}
