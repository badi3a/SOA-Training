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
        resources.add(webservices.UERessources.class);
        resources.add(webservices.HelloRessources.class);

        // Swagger OpenAPI
        resources.add(OpenApiResource.class);
        return resources;
    }
}
