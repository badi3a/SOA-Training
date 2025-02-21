package webservices;

import entities.RendezVous;
import metiers.RendezVousBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rendezvous")
public class RendezVousRessources {
    static RendezVousBusiness rendezVousBusiness = new RendezVousBusiness();

    // GET all RendezVous
    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRendezVous() {
        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(rendezVousBusiness.getListeRendezVous())
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
                    .entity("{\"message\":\"Rendez-vous non trouvé\"}")
                    .build();
        }
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRendezVous(RendezVous rendezVous) {
        boolean added = rendezVousBusiness.addRendezVous(rendezVous);
        if (added) {
            return Response.status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("{\"message\":\"Rendez-vous ajouté avec succès\"}")
                    .build();
        } else {
            return Response.status(404)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("{\"message\":\"Échec de l'ajout du rendez-vous\"}")
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
                    .entity("{\"message\":\"Rendez-vous mis à jour avec succès\"}")
                    .build();
        } else {
            return Response.status(404)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("{\"message\":\"Rendez-vous non trouvé\"}")
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
                    .entity("{\"message\":\"Rendez-vous supprimé avec succès\"}")
                    .build();
        } else {
            return Response.status(404)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("{\"message\":\"Rendez-vous non trouvé\"}")
                    .build();
        }
    }
}

