package webservices;

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("api")
public class ApplicationMain extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();

        resources.add(OpenApiResource.class);
        resources.add(UERessources.class);
        resources.add(ModuleRessources.class);
        resources.add(HelloRessources.class);

        return resources;
    }
}