package restApi;

import entities.RendezVous;
import metiers.RendezVousBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rendezVous")
public class RendezVousRestApi {

    static RendezVousBusiness lVHelperid = new RendezVousBusiness();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listrendezVous() {
        return Response.ok(lVHelperid.getListeRendezVous()).build();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRendezVous(@PathParam("id") int id) {
        return Response.ok(lVHelperid.getRendezVousById(id)).build();
    }

    @Path("/reference")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getrendezVousByRefLogement(@QueryParam("ref") int ref) {
        return Response.ok(lVHelperid.getListeRendezVousByLogementReference(ref)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRendezVous(RendezVous rendezVous) {
        lVHelperid.addRendezVous(rendezVous);
        return Response.ok(lVHelperid.getListeRendezVous()).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response putrendezVous(@PathParam("id") int id, RendezVous rend) {
        lVHelperid.updateRendezVous(id, rend);
        return Response.ok(lVHelperid.getListeRendezVous()).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response deleterendezVous(@PathParam("id") int id) {
        lVHelperid.deleteRendezVous(id);
        return Response.ok(lVHelperid.getListeRendezVous()).build();
    }
}
