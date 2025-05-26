package restAPI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
public class helloRestApi {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/hi")
    public Response songBingo(){
        return Response.status(200).entity("Hello world !! ").build();
    }
}
