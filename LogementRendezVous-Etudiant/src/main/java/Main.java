import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("api")
public class Main extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(WebServices.CORSFilter.class);
        resources.add(WebServices.LogementAPI.class);
        resources.add(WebServices.RendezVousAPI.class);
        return resources;
    }

}
