package webService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
public class HelloRestAPI {


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response sayHello(){
        return  Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity("Hello")
                .build();
    }


}
