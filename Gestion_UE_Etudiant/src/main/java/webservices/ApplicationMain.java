
package webservices;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("api")
public class ApplicationMain extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(UniteEnseignementRessource.class);
        resources.add(ModuleRessource.class);
        resources.add(HelloRessources.class);
        resources.add(org.glassfish.jersey.jackson.JacksonFeature.class);
        return resources;
    }
}
