package restAPI;

import entities.Logement;
import metiers.LogementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/logement")
public class LogementRestApi {
    static LogementBusiness  lBHelper=new LogementBusiness();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //get all logement
    public Response listLogement(){
        return Response.status(200).entity(lBHelper.getLogements()).build();
    }
    @Path("/{ref}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //get  logement by ref
    public Response getLogement(@PathParam(value = "ref") int ref){
        return Response.status(200).entity(lBHelper.getLogementsByReference(ref)).build();
    }

    @Path("/deleguation")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //get  logement by deleguation
    public Response getLogementByDeleguation(@QueryParam(value = "deg") String deg){
        return Response.status(200).entity(lBHelper.getLogementsByDeleguation(deg)).build();
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    //add  logement
    public Response addLogement(Logement log){
       lBHelper.addLogement(log);
        return Response.status(201).entity("Logement added successfully").build();
    }
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{ref}")
    //put  logement
    public Response putLogement(@PathParam(value = "ref") int ref,Logement log){
        lBHelper.updateLogement(ref,log);
        return Response.status(201).entity("Logement updated successfully").build();
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{ref}")
    //delete  logement
    public Response deleteLogement(@PathParam(value = "ref") int ref){
        lBHelper.deleteLogement(ref);
        return Response.status(201).entity("Logement deleted successfully").build();
    }
}
