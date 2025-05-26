package restAPI;



import entities.RendezVous;
import metiers.RendezVousBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rendezVous")
public class RendezVousRestApi {
    static RendezVousBusiness lVHelperid=new RendezVousBusiness();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //get all rendezVous
    public Response listrendezVous(){
        return Response.status(200).entity(lVHelperid.getListeRendezVous()).build();
    }
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //get  rendezVous by id
    public Response getRendezVous(@PathParam(value = "id") int id){
        return Response.status(200).entity(lVHelperid.getRendezVousById(id)).build();
    }

    @Path("/reference")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //get rendezVous by ref logement
    public Response getrendezVousByRefLogement(@QueryParam(value = "ref") int ref){
        return Response.status(200).entity(lVHelperid.getListeRendezVousByLogementReference(ref)).build();
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    //add RendezVous
    public Response addRendezVous(RendezVous rendezVous) {
        try {
            System.out.println("Received RendezVous: " + (rendezVous != null ? rendezVous.getId() : "null"));

            boolean success = lVHelperid.addRendezVous(rendezVous);
            if (success) {
                return Response.status(201).entity("RendezVous added successfully").build();
            } else {
                return Response.status(400).entity("Failed to add RendezVous - check if Logement exists").build();
            }
        } catch (Exception e) {
            System.err.println("Error adding RendezVous: " + e.getMessage());
            e.printStackTrace();
            return Response.status(500).entity("Error adding rendez-vous: " + e.getMessage()).build();
        }
    }

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    //put  rendezVous
    public Response putrendezVous(@PathParam(value = "id") int id,RendezVous rend){
        lVHelperid.updateRendezVous(id,rend);
        return Response.status(201).entity("rendezVous updated successfully").build();
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{id}")
    //delete  rendezVous
    public Response deleterendezVous(@PathParam(value = "id") int id){
        lVHelperid.deleteRendezVous(id);
        return Response.status(201).entity("rendezVous deleted successfully").build();
    }
}
