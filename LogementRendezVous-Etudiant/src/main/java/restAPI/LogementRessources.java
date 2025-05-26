package restAPI;

import entities.Logement;
import metiers.LogementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@ApplicationPath("api")
@Path("/logement")
public class LogementRessources extends Application {
    static LogementBusiness help = new LogementBusiness();
    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        return Response.
                status(200).header("Access-Control-Allow-Origin", "*").
                entity(help.getLogements()).
                build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOne(@PathParam("id") int id) {
        Logement logement = help.getLogementsByReference(id);
        return Response.
                status(200).header("Access-Control-Allow-Origin", "*").
                entity(logement).
                build();
    }

    @GET
    @Path("/deleguation")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogementsByDeleguation (@QueryParam("deleguation") String deleguation) {

        return Response.
                status(200).header("Access-Control-Allow-Origin", "*").
                entity(help.getLogementsByDeleguation(deleguation)).
                build();
    }

    @POST
    @Path("/addOne")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOne(Logement logement){
        help.addLogement(logement);
        return Response.
                status(201).header("Access-Control-Allow-Origin", "*").
                entity("logement added succusesfully").
                build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOne(@PathParam("id") int id, Logement logement){
        help.updateLogement(id,logement);
        return Response.
                status(201).header("Access-Control-Allow-Origin", "*").
                entity(logement).
                build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteOne(@PathParam("id") int id, Logement logement){
        help.deleteLogement(id);
        return Response.
                status(200).header("Access-Control-Allow-Origin", "*").
                entity("deleted succssfully").
                build();
    }

}