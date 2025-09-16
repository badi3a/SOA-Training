
package WebServices;

import entities.Logement;
import entities.RendezVous;
import metiers.RendezVousBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/rendezvous")
public class RendezVousAPI {
    RendezVousBusiness rendezVousBusiness = new RendezVousBusiness();

    @GET
    @Path("/getall")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRendezVous() {
        try {
            List<RendezVous> rdv = rendezVousBusiness.getListeRendezVous();
            if (rdv == null || rdv.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("No Rendezvous found")
                        .build();
            }
            return Response.ok(rdv).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving rdvs: " + e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/add")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addLogement(RendezVous rendezVous) {
        try {
            boolean rdv = rendezVousBusiness.addRendezVous(rendezVous);

            return Response.ok(rdv).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error add rdvs: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/getbylogementreference/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRendezvousByLogementreference(@PathParam("reference") int reference) {
        try {
            List<RendezVous> rdv = rendezVousBusiness.getListeRendezVousByLogementReference(reference);
            if (rdv == null || rdv.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("No Rendezvous found for logement reference: " + reference)
                        .build();
            }
            return Response.ok(rdv).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving rdvs: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/getbyid/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRendezvousById(@PathParam("reference") int reference) {
        try {
            RendezVous rdv = rendezVousBusiness.getRendezVousById(reference);
            if (rdv == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Rendezvous not found for reference: " + reference)
                        .build();
            }

            return Response.ok(rdv).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving rdvs: " + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/delete/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRendezvousById(@PathParam("reference") int reference) {
        try {
            Boolean rdv = rendezVousBusiness.deleteRendezVous(reference);

            return Response.ok(rdv).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error deleting rdvs: " + e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/update/{reference}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateRendezvousB(@PathParam("reference") int reference, RendezVous rendezVous) {
        try {
            Boolean rdv = rendezVousBusiness.updateRendezVous(reference, rendezVous);

            return Response.ok(rdv).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error updating rdvs: " + e.getMessage())
                    .build();
        }
    }
}