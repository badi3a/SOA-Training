package RestAPI;

import entities.RendezVous;
import metiers.RendezVousBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/rendezvous")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RendezVousRestApi {

   static RendezVousBusiness rvBusiness = new RendezVousBusiness();

    @GET
    public Response getAllRendezVous() {
        List<RendezVous> list = rvBusiness.getListeRendezVous();
        return Response.ok(list).build();
    }

    @GET
    @Path("/{id}")
    public Response getRendezVousById(@PathParam("id") int id) {
        RendezVous rv = rvBusiness.getRendezVousById(id);
        if (rv != null) {
            return Response.ok(rv).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Rendez-vous avec ID " + id + " non trouvé.").build();
        }
    }

    @GET
    @Path("/logement/{ref}")
    public Response getRendezVousByLogementReference(@PathParam("ref") int ref) {
        List<RendezVous> list = rvBusiness.getListeRendezVousByLogementReference(ref);
        return Response.ok(list).build();
    }

    @POST
    public Response addRendezVous(RendezVous rv) {
        boolean added = rvBusiness.addRendezVous(rv);
        if (added) {
            return Response.status(Response.Status.CREATED).entity(rv).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Logement non trouvé ou invalide.").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateRendezVous(@PathParam("id") int id, RendezVous updatedRv) {
        boolean updated = rvBusiness.updateRendezVous(id, updatedRv);
        if (updated) {
            return Response.ok(updatedRv).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Échec de la mise à jour : ID ou logement invalide.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteRendezVous(@PathParam("id") int id) {
        boolean deleted = rvBusiness.deleteRendezVous(id);
        if (deleted) {
            return Response.ok("Rendez-vous supprimé avec succès.").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Rendez-vous avec ID " + id + " non trouvé.").build();
        }
    }
}
