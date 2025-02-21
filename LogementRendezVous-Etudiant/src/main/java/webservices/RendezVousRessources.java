package webservices;

import entities.Logement;
import entities.RendezVous;
import metiers.LogementBusiness;
import metiers.RendezVousBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/rendezvous")
public class RendezVousRessources {
    static RendezVousBusiness rendezVousBusiness = new RendezVousBusiness();

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRendezVous(RendezVous rendezVous) {
        boolean added = rendezVousBusiness.addRendezVous(rendezVous);
        if (added) {
            return Response.status(201)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("{\"message\": \"RendezVous added successfully\"}")
                    .build();
        } else {
            return Response.status(400)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("{\"error\": \"Failed to add RendezVous\"}")
                    .build();
        }
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRendezVous() {
        List<RendezVous> liste = rendezVousBusiness.getListeRendezVous();
        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(liste)
                .build();
    }

    @GET
    @Path("/getByLogement/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRendezVousByLogement(@PathParam("reference") int reference) {
        List<RendezVous> liste = rendezVousBusiness.getListeRendezVousByLogementReference(reference);
        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(liste)
                .build();
    }

    @GET
    @Path("/getById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRendezVousById(@PathParam("id") int id) {
        RendezVous rendezVous = rendezVousBusiness.getRendezVousById(id);
        if (rendezVous != null) {
            return Response.status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity(rendezVous)
                    .build();
        } else {
            return Response.status(404)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("{\"error\": \"RendezVous not found\"}")
                    .build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRendezVous(@PathParam("id") int id) {
        boolean deleted = rendezVousBusiness.deleteRendezVous(id);
        if (deleted) {
            return Response.status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("{\"message\": \"RendezVous deleted successfully\"}")
                    .build();
        } else {
            return Response.status(404)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("{\"error\": \"RendezVous not found\"}")
                    .build();
        }
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRendezVous(@PathParam("id") int id, RendezVous updatedRendezVous) {
        boolean updated = rendezVousBusiness.updateRendezVous(id, updatedRendezVous);
        if (updated) {
            return Response.status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("{\"message\": \"RendezVous updated successfully\"}")
                    .build();
        } else {
            return Response.status(404)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("{\"error\": \"RendezVous not found or invalid logement reference\"}")
                    .build();
        }
    }
}
