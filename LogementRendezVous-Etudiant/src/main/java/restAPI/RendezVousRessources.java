package restAPI;

import entities.RendezVous;
import metiers.RendezVousBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rendezvous")
public class RendezVousRessources {
    static RendezVousBusiness rendezVousBusiness = new RendezVousBusiness();

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(rendezVousBusiness.getListeRendezVous())
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOne(@PathParam("id") int id) {
        RendezVous rendezVous = rendezVousBusiness.getRendezVousById(id);
            return Response
                    .status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity(rendezVous)
                    .build();
    }

    @POST
    @Path("/addOne")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOne(RendezVous rendezVous) {
        rendezVousBusiness.addRendezVous(rendezVous);
            return Response
                    .status(201)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("Rendez-vous added successfully")
                    .build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOne(@PathParam("id") int id, RendezVous rendezVous) {
        rendezVousBusiness.updateRendezVous(id, rendezVous);
            return Response
                    .status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity(rendezVousBusiness.getRendezVousById(id))
                    .build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteOne(@PathParam("id") int id) {
        rendezVousBusiness.deleteRendezVous(id);
            return Response
                    .status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("Rendez-vous deleted successfully")
                    .build();
    }
}