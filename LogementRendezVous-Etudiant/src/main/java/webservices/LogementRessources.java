package webservices;

import entities.Logement;
import metiers.LogementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/logement")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LogementRessources {
    static LogementBusiness logementBusiness = new LogementBusiness();

    @GET
    @Path("/getAll")
    public Response getAll() {
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .entity(logementBusiness.getLogements())
                .build();
    }

    @POST
    @Path("/new")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addLogement(Logement logement) {
        boolean added = logementBusiness.addLogement(logement);
        if (added) {
            return Response.status(Response.Status.CREATED)
                    .header("Access-Control-Allow-Origin", "*")  // Allow any origin
                    .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")  // Allow methods you need
                    .header("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With")  // Allow specific headers
                    .header("Access-Control-Allow-Credentials", "true")  // Allow credentials (if you need them)
                    .entity("Logement ajouté avec succès")
                    .build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("Erreur lors de l'ajout du logement")
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    @PUT
    @Path("/update/{reference}")
    public Response updateLogement(@PathParam("reference") int reference, Logement logement) {
        boolean updated = logementBusiness.updateLogement(reference, logement);
        if (updated) {
            return Response.ok()
                    .entity("Logement mis à jour avec succès")
                    .build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity("Logement introuvable")
                .build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/delete/{reference}")
    public Response deleteLogement(@PathParam("reference") int reference) {
        boolean deleted = logementBusiness.deleteLogement(reference);
        if (deleted) {
            return Response.ok().entity("Logement supprimé avec succès").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Logement non trouvé").build();
        }
    }


    @GET
    @Path("/{reference}")
    public Response getLogementsByReference(@PathParam("reference") int reference) {
        Logement logement = logementBusiness.getLogementsByReference(reference);
        if (logement != null) {
            return Response.ok()
                    .entity(logement)
                    .build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity("Logement introuvable")
                .build();
    }
}
