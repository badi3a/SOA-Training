package webservices;

// Import the necessary JAX-RS (Java API for RESTful Web Services) annotations and classes
import javax.ws.rs.GET;               // Annotation to indicate that a method responds to an HTTP GET request
import javax.ws.rs.Path;              // Annotation to define the URL path of a resource
import javax.ws.rs.PathParam;         // Annotation to extract dynamic values from the URL
import javax.ws.rs.Produces;          // Annotation to define the type of content returned (text, JSON, XML…)
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;    // Provides constants for standard media types (e.g., TEXT_PLAIN, APPLICATION_JSON…)
import javax.ws.rs.core.Response;     // Class used to build a custom HTTP response

// The resource will be accessible at the base URL followed by /hello
@Path("/hello")
public class HelloRessources {

    // This method handles GET requests sent to /hello/hi
    @GET
    @Path("/hi")
    @Produces(MediaType.TEXT_PLAIN)   // The response will be returned as plain text
    public Response sayHello() {
        // Build an HTTP 200 (OK) response with the message "Hello World!"
        return Response
                .status(200)                  // HTTP status code 200 = success
                .entity("Hello World!")       // The body of the response
                .build();                     // Builds the final Response object
    }

    // This method handles GET requests sent to /hello/{name}
    // Example: /hello/Ahmed -> will return "Hello Ahmed!"
    @GET
    @Path("/{name}")                          // {name} is a dynamic variable in the URL
    @Produces(MediaType.TEXT_PLAIN)
    public Response sayHelloT(@PathParam(value = "name") String name) {
        // Build a personalized response message using the provided name
        return Response
                .status(200)
                .entity("Hello " + name + "!") // Insert the name from the URL into the response
                .build();
    }
}
