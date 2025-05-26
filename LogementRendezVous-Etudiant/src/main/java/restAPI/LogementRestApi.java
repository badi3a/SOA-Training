package restAPI;


import entities.Logement;
import metiers.LogementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/logements")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LogementRestApi {

    private static LogementBusiness logementBusiness = new LogementBusiness();

    // GET all logements
    @GET
    public Response getAllLogements() {
        List<Logement> logements = logementBusiness.getLogements();
        return Response.ok(logements).build();
    }

    // GET logement by reference
    @GET
    @Path("/{ref}")
    public Response getLogementByReference(@PathParam("ref") int ref) {
        Logement logement = logementBusiness.getLogementsByReference(ref);
        if (logement != null) {
            return Response.ok(logement).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Logement avec référence " + ref + " introuvable.")
                    .build();
        }
    }

//    // GET logements by délégation ID
//    @GET
//    @Path("/delegation/{delegation}")
//    public Response getLogementsByDelegation(@PathParam("delegation") String delegation) {
//        List<Logement> logements = logementBusiness.getLogementsByDeleguation(delegation);
//        return Response.ok(logements).build();
//    }

    // GET logements by délégation
    @GET
    @Path("/delegation")
    public Response getLogementsByDelegation(@QueryParam( value = "deleg") String delegation) {
        List<Logement> logements = logementBusiness.getLogementsByDeleguation(delegation);
        return Response.ok(logements).build();
    }




    // POST (add) logement
    @POST
    public Response addLogement(Logement logement) {
        boolean added = logementBusiness.addLogement(logement);
        if (added) {
            return Response.status(Response.Status.CREATED)
                    .entity("Logement ajouté avec succès.")
                    .build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Échec de l'ajout du logement.")
                    .build();
        }
    }

    // PUT (update) logement
    @PUT
    @Path("/{ref}")
    public Response updateLogement(@PathParam("ref") int ref, Logement logement) {
        boolean updated = logementBusiness.updateLogement(ref, logement);
        if (updated) {
            return Response.ok("Logement mis à jour avec succès.").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Logement avec référence " + ref + " introuvable.")
                    .build();
        }
    }


    // DELETE logement
    @DELETE
    @Path("/{ref}")
    public Response deleteLogement(@PathParam("ref") int ref) {
        boolean deleted = logementBusiness.deleteLogement(ref);
        if (deleted) {
            return Response.ok("Logement supprimé avec succès.").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Logement avec référence " + ref + " introuvable.")
                    .build();
        }
    }


}
