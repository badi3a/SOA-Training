package config;

import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;


import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api") // correspond à basePath dans SwaggerConfig
public class ApplicationMain extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        // Ajouter tes ressources
        resources.add(webservices.LogementRessources.class);
        resources.add(webservices.RendezVousRessources.class);

        // Ajouter les classes Swagger
        resources.add(ApiListingResource.class);
        resources.add(SwaggerSerializers.class);
        return resources;
    }
}
