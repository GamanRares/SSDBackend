package SSDBackend.Application;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class RESTApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();
        classes.add(RESTUser.class);
        classes.add(RESTGame.class);
        classes.add(RESTRole.class);
        classes.add(RESTUserGame.class);

        return classes;
    }

}
