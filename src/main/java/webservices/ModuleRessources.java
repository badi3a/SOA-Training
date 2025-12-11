package webservices;

// Import the necessary JAX-RS (Java API for RESTful Web Services) annotations and classes
import entities.UniteEnseignement;

import javax.ws.rs.GET;               // Annotation to indicate that a method responds to an HTTP GET request
import javax.ws.rs.Path;              // Annotation to define the URL path of a resource
import javax.ws.rs.Produces;          // Annotation to define the type of content returned (text, JSON, XML…)
import javax.ws.rs.core.MediaType;    // Provides constants for standard media types (e.g., TEXT_PLAIN, APPLICATION_JSON…)
import javax.ws.rs.core.Response;     // Class used to build a custom HTTP response

import static webservices.UERessources.helper;

// The resource will be accessible at the base URL followed by /hello
@Path("/hello")
public class ModuleRessources {

    // This method handles GET requests sent to /hello/hi
    @GET
    @Path("/hi")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllModuuls() {
        return Response
                .status(200)
                .entity(helper.getListeUE())
                .build();
    }


}
