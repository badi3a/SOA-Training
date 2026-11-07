package rest.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/test")
public class TestResource {

    @GET
    public Response test() {
        return Response.ok("REST fonctionne  Marwa ").build();
    }
}
