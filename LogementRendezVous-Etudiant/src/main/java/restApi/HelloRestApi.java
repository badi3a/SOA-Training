package restApi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//exposition du web service
//ressource = web service = endpoint =restapi
@Path("/hello")  //specifier la route vers la ressource
public class HelloRestApi {
    //web service --> return hello to the client
    //response --> http REQUEST / Header + Body
    @GET //pr dire c est une ressources a consommer à travaers une requette http de type get
    @Produces (MediaType.TEXT_PLAIN)  //pr specifier le type de retour text, json,xml,html
    public Response sayHello() {
        return Response
                .status(200)
                .entity("Hello World!")
                .build();
    }
}
