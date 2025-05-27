package config;
import io.swagger.jaxrs.config.BeanConfig;
import org.glassfish.jersey.server.ResourceConfig;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class SwaggerConfig extends ResourceConfig {

    public SwaggerConfig() {
        // Scanne le package de tes ressources
        packages("webservices");

        // Ajoute Swagger endpoints
        register(io.swagger.jaxrs.listing.ApiListingResource.class);
        register(io.swagger.jaxrs.listing.SwaggerSerializers.class);

        // Configuration Swagger
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080"); // adapter selon ton port
        beanConfig.setBasePath("/LogementRendezVous-Etudiant/api");
        beanConfig.setResourcePackage("webservices");
        beanConfig.setTitle("API Logement");
        beanConfig.setDescription("Documentation Swagger avec version 1.5.22");
        beanConfig.setScan(true);
    }
}