package restApi;

import metiers.LogementBusiness;
import entities.Logement;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@Path("/logement")


public class LogementRestApi {
    static LogementBusiness lBHelper = new LogementBusiness();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listeLogement() {
        return Response.status(200).entity(lBHelper.getLogements()).build();
    }
    @Path("/{ref}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogByRef(@PathParam(value = "ref") int ref) {
        return Response.status(200).entity(lBHelper.getLogementsByReference(ref)).build();
    }

    @Path("/deleg")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogementByDeleguation(@QueryParam(value = "delg") String deg){
        return Response.status(200).entity(lBHelper.getLogementsByDeleguation(deg)).build();
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addLogement(Logement logement) {

        lBHelper.addLogement(logement);
        return Response
                .status(200)
                .entity("Logement added")
                .build();
    }


    @PUT
    @Path("/{ref}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLogement(@PathParam("ref") int ref, Logement logement) {
        logement.setReference(ref);
        lBHelper.updateLogement(ref, logement);
        return Response.status(200).entity(lBHelper.getLogements()).build();
    }


    @DELETE
    @Path("/{ref}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteLogement(@PathParam("ref") int ref) {
        lBHelper.deleteLogement(ref);
        return Response.status(200).entity(lBHelper.getLogements()).build();
    }
}
