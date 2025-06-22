package WebServices;

import entities.Logement;
import metiers.LogementBusiness;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/hello")
public class HelloAPI {
    @GET
    @Path("/gethello")
    @Produces(MediaType.TEXT_PLAIN)
    public Response hello(){
    return Response.ok("Hello World").build();
    }



}
