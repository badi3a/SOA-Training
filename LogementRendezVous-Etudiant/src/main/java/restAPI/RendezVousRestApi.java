package restAPI;



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

    private RendezVousBusiness rendezVousMetier = new RendezVousBusiness();

    // GET /rendezvous
    @GET
    public Response getAllRendezVous() {
        List<RendezVous> liste = rendezVousMetier.getListeRendezVous();
        return Response.ok(liste).build();
    }

    // GET /rendezvous/{id}
    @GET
    @Path("/{id}")
    public Response getRendezVousById(@PathParam("id") int id) {
        RendezVous rdv = rendezVousMetier.getRendezVousById(id);
        if (rdv != null) {
            return Response.ok(rdv).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Rendez-vous non trouvé").build();
        }
    }

    // GET /rendezvous/logement/{reference}
    @GET
    @Path("/logement/{reference}")
    public Response getRendezVousByLogementReference(@PathParam("reference") int reference) {
        List<RendezVous> liste = rendezVousMetier.getListeRendezVousByLogementReference(reference);
        return Response.ok(liste).build();
    }

    // POST /rendezvous
    @POST
    public Response createRendezVous(RendezVous rendezVous) {
        boolean added = rendezVousMetier.addRendezVous(rendezVous);
        if (added) {
            return Response.status(Response.Status.CREATED).entity("Rendez-vous ajouté avec succès").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Erreur lors de l'ajout (logement invalide)").build();
        }
    }

    // PUT /rendezvous/{id}
    @PUT
    @Path("/{id}")
    public Response updateRendezVous(@PathParam("id") int id, RendezVous updatedRendezVous) {
        boolean updated = rendezVousMetier.updateRendezVous(id, updatedRendezVous);
        if (updated) {
            return Response.ok("Rendez-vous mis à jour").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Rendez-vous introuvable ou logement invalide").build();
        }
    }

    // DELETE /rendezvous/{id}
    @DELETE
    @Path("/{id}")
    public Response deleteRendezVous(@PathParam("id") int id) {
        boolean deleted = rendezVousMetier.deleteRendezVous(id);
        if (deleted) {
            return Response.ok("Rendez-vous supprimé").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Rendez-vous introuvable").build();
        }
    }

}
