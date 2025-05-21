package webservices;
import entities.RendezVous;
import metiers.RendezVousBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
@Path("/rendezvous")
public class RendezVousRessources {
    static RendezVousBusiness rendezVousBusiness = new RendezVousBusiness();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public Response getAll() {

        return Response.ok().entity(rendezVousBusiness.getListeRendezVous()).build();
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/new")
    public Response addRendezVous(RendezVous rendezVous) {

        return Response.status(201).entity("Rendez-vous ajouté avec succès").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getById(@PathParam("id") int id) {
        RendezVous rendezVous = rendezVousBusiness.getRendezVousById(id);

        return Response.ok().entity(rendezVous).build();
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/delete/{id}")
    public Response deleteRendezVous(@PathParam("id") int id) {

        return Response.ok().entity("Rendez-vous supprimé avec succès").build();
    }

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update/{id}")
    public Response updateRendezVous(@PathParam("id") int id, RendezVous updatedRendezVous) {

        return Response.ok().entity("Rendez-vous mis à jour avec succès").build();

    }
}
