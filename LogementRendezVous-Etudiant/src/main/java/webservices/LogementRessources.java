package webservices;

import entities.Logement;
import metiers.LogementBusiness;
import io.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/logement")
@Api(value = "/logement", description = "Opérations sur les logements")

public class LogementRessources {
    LogementBusiness help = new LogementBusiness();

    // GET ALL logements
    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Afficher tous les logements", response = Logement.class, responseContainer = "List")

    public Response getAll() {

        return Response.ok(help.getLogements()).build();
        //        return Response.status(200)
//                .header("Access-Control-Allow-Origin", "*")
//                .entity(help.getLogements())
//                .build();
    }

    // GET logement by reference
    @GET
    @Path("/get/{ref}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogByRef(@PathParam("ref") int ref) {
        Logement logement = help.getLogementsByReference(ref);
        if (logement != null) {
            return Response.status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity(logement)
                    .build();
        } else {
            return Response.status(404)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("Logement non trouvé")
                    .build();
        }
    }

    // POST - Ajouter un logement
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addLogement(Logement logement) {
        boolean success = help.addLogement(logement);
        if (success) {
            return Response.status(201)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("Logement ajouté avec succès")
                    .build();
        } else {
            return Response.status(400)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("Erreur lors de l'ajout")
                    .build();
        }
    }

    // PUT - Modifier un logement (par référence)
    @PUT
    @Path("/update/{ref}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLogement(@PathParam("ref") int ref, Logement logement) {
        boolean success = help.updateLogement(ref, logement);
        if (success) {
            return Response.status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("Logement modifié avec succès")
                    .build();
        } else {
            return Response.status(404)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("Logement non trouvé pour modification")
                    .build();
        }
    }

    // DELETE - Supprimer un logement
    @DELETE
    @Path("/delete/{ref}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteLogement(@PathParam("ref") int ref) {
        boolean success = help.deleteLogement(ref);
        if (success) {
            return Response.status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("Logement supprimé avec succès")
                    .build();
        } else {
            return Response.status(404)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("Logement non trouvé pour suppression")
                    .build();
        }
    }


}
