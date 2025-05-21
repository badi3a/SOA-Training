package restApi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@Path("/hello")
public class HelloRest {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response sayHello(){
        return Response.status(200).
                entity("Hello World!").
                build();

    }
}
