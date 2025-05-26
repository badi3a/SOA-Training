package restApi;

import entities.Logement;
import metiers.LogementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/logement")
public class LogementRestApi {
    LogementBusiness help = new LogementBusiness();


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOne (Logement logement) {
        boolean added = help.addLogement(logement);
        if (added){

    } return Response.
                status(201).header("Access-Control-Allow-Origin", "*").
                entity(logement).
                build();}


    @GET
    @Path("/getLogements")
    @Produces(MediaType.APPLICATION_JSON)
    public Response  getAll(){
        return Response.
                status(200).header("Access-Control-Allow-Origin", "*").
                entity(help.getLogements()).
                build();
    }
    @GET
    @Path("/by-ref/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response  getLogementsByRef(@PathParam(value = "reference") int reference){
        return Response.
                status(200).header("Access-Control-Allow-Origin", "*").
                entity(help. getLogementsByReference(reference)).
                build();
    }


    @GET
    @Path("/by-del/{delegation}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogementsByDeleguation(@PathParam("delegation") String delegation) {
        List<Logement> logements = help.getLogementsByDeleguation(delegation);
        return Response
                .ok(logements)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    @GET
    @Path("/listLogby-ref/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogementsListeByref(@PathParam("reference") int reference) {
        List<Logement> logements = help.getLogementsListeByref(reference);
        return Response
                .ok(logements)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    @DELETE
    @Path("/delete/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteLogementByReference(@PathParam("reference") int reference) {
        boolean isDeleted = help.deleteLogement(reference);

        if (isDeleted) {
            return Response
                    .status(200)
                    .entity("Logement supprimé avec succès")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Aucun logement trouvé avec cette référence")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        }
    }

    @PUT
    @Path("/update/{reference}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLogementByReference(@PathParam("reference") int reference, Logement logement) {
        boolean updated = help.updateLogement(reference, logement);

        if (updated) {
            return Response
                    .status(200)
                    .entity(logement)
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Aucun logement trouvé avec cette référence")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        }
    }

    @PUT
    @Path("/setlogements")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response setAllLogements(List<Logement> logements) {
        help.setLogements(logements);
        return Response
                .status(200)
                .entity(logements)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }


}
