package SSDBackend.BackingBeans.BusinessLogic;

import lombok.Data;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;

@Data
@Stateless
public class UserGameEJB implements Serializable {

    @Inject
    private BusinessLogic businessLogic;

    @Inject
    private UserEJB userEJB;

    @Inject
    private GameEJB gameEJB;

}
