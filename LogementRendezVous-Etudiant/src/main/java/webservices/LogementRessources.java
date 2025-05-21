package webservices;

import entities.Logement;
import metiers.LogementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/logement")
public class LogementRessources {
    LogementBusiness help = new LogementBusiness();

   // Afficher la liste totale des logements
    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response  getAll(){
        List<Logement> logements = help.getLogements();
        if (logements != null) {
        return Response.
                status(200).header("Access-Control-Allow-Origin", "*").
                entity(logements).
                build();
        } else {
            return Response.status(404)
                    .entity("Logements non trouvés")
                    .build();
        }
    }

    // Afficher un logement à partir de sa référence
    @GET
    @Path("/getByRef/{ref}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByReference(@PathParam("ref") int reference) {
        Logement logement = help.getLogementsByReference(reference);
        if (logement != null) {
            return Response.status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity(logement)
                    .build();
        } else {
            return Response.status(404)
                    .entity("Logement non trouvé")
                    .build();
        }
    }

    // Afficher les logements d'une délégation
    @GET
    @Path("/getByDelegation/{delegation}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByDelegation(@PathParam("delegation") String delegation) {
        List<Logement> logements = help.getLogementsByDeleguation(delegation);
        if (logements != null) {
            return Response.status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity(logements)
                    .build();

        } else {
            return Response.status(404)
                    .entity("Pas de logement dans cette délégation")
                    .build();
        }
    }

    // Ajouter un nouveau logement
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addLogement(Logement logement) {
        boolean added = help.addLogement(logement);
        if (added) {
            return Response.status(201)
                    .entity("Logement ajouté avec succès")
                    .build();
        } else {
            return Response.status(500)
                    .entity("Échec de l'ajout du logement")
                    .build();
        }
    }

    // Supprimer un logement
    @DELETE
    @Path("/delete/{ref}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteLogement(@PathParam("ref") int reference) {
        boolean deleted = help.deleteLogement(reference);
        if (deleted) {
            return Response.status(200)
                    .entity("Logement supprimé avec succès")
                    .build();
        } else {
            return Response.status(404)
                    .entity("Logement introuvable")
                    .build();
        }
    }

    // Mettre à jour un logement
    @PATCH
    @Path("/update/{ref}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateLogement(@PathParam("ref") int reference, Logement logement) {
        boolean updated = help.updateLogement(reference, logement);
        if (updated) {
            return Response.status(200)
                    .entity("Logement mis à jour avec succès")
                    .build();
        } else {
            return Response.status(404)
                    .entity("Logement non trouvé pour la mise à jour")
                    .build();
        }
    }

}
