package RestAPI;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@Path("/hi")
public class HelloRestAPI {

    @GET

    @Produces(MediaType.TEXT_PLAIN)

    @Path("/hello")

    public javax.ws.rs.core.Response sayHello() {

        return Response.status(200).entity("hello world!").build();
    }

    }
