package webservices;

import entities.Logement;
import metiers.LogementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/logement")
public class LogementRessources {
    static LogementBusiness help = new LogementBusiness();

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(help.getLogements())
                .build();
    }

    @GET
    @Path("/byDelegation")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogementsByDeleguation(@QueryParam("delegation") String delegation) {
            return Response
                    .status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity(help.getLogementsByDeleguation(delegation))
                    .build();
        }

    @GET
    @Path("/byReference/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogementsByReference(@QueryParam("reference") int reference) {
        return Response
                .status(Response.Status.OK) // 200 OK
                .header("Access-Control-Allow-Origin", "*")
                .entity(help.getLogementsByReference(reference))
                .build();
    }

    @GET
    @Path("/ListeByref/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogementsListeByref(@QueryParam("reference") int reference) {
        return Response
                .status(Response.Status.OK) // 200 OK
                .header("Access-Control-Allow-Origin", "*")
                .entity(help.getLogementsListeByref(reference))
                .build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addLogement(Logement logement) {
        boolean isAdded = help.addLogement(logement);
        if (isAdded) {
            return Response
                    .status(Response.Status.CREATED) // 201 Created
                    .header("Access-Control-Allow-Origin", "*")
                    .entity(logement)
                    .build();

        } else {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR) // 500 Internal Server Error
                    .entity("Failed to add logement.")
                    .build();
        }
    }

    @DELETE
    @Path("/delete/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteLogement(@QueryParam("reference") int reference) {
        boolean isDeleted = help.deleteLogement(reference);
        if (isDeleted) {
            return Response
                    .status(Response.Status.NO_CONTENT) // 204 No Content
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND) // 404 Not Found
                    .entity("Logement with reference " + reference + " not found.")
                    .build();
        }
    }
    @PUT

    @Path("/update/{reference}")

    @Consumes(MediaType.APPLICATION_JSON)

    @Produces(MediaType.APPLICATION_JSON)

    public Response updateLogement(@PathParam("reference") int reference, Logement updatedLogement) {
        boolean isUpdated = help.updateLogement(reference, updatedLogement);
        if (isUpdated) {
            return Response
                    .status(Response.Status.OK) // 200 OK
                    .header("Access-Control-Allow-Origin", "*")
                    .entity(updatedLogement) // Return the updated logement
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND) // 404 Not Found
                    .entity("Logement with reference " + reference + " not found.")
                    .build();
        }
    }

    @PUT

    @Path("/setLogements")

    @Consumes(MediaType.APPLICATION_JSON)

    @Produces(MediaType.APPLICATION_JSON)

    public Response setLogements(List<Logement> logements) {
        help.setLogements(logements);
        return Response
                .status(Response.Status.OK) // 200 OK
                .header("Access-Control-Allow-Origin", "*")
                .entity("Logements updated successfully.")
                .build();
    }
}
























