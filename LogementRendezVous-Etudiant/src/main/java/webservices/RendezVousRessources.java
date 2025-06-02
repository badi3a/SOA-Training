package webservices;
import entities.RendezVous;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import metiers.RendezVousBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/rendezvous")
@Api(value = "/rendezvous", description = "Opérations sur les rendez-vous") // Added Swagger API annotation
public class RendezVousRessources {
    RendezVousBusiness business = new RendezVousBusiness();

    // Ajouter un rendez-vous
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @ApiOperation(value = "Ajouter un nouveau rendez-vous", response = String.class)
    public Response addRendezVous(RendezVous rendezVous) {
        boolean added = business.addRendezVous(rendezVous);
        if (added) {
            return Response.status(201).entity("Rendez-vous ajouté avec succès").build();
        } else {
            return Response.status(400).entity("Le logement n'existe pas, rendez-vous non ajouté").build();
        }
    }

    // Afficher tous les rendez-vous
    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Récupérer la liste de tous les rendez-vous", response = RendezVous.class, responseContainer = "List")
    public Response getAllRendezVous() {
        List<RendezVous> liste = business.getListeRendezVous();
        return Response.ok(liste).build();
    }

    // Afficher un rendez-vous par ID
    @GET
    @Path("/getById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Récupérer un rendez-vous par son ID", response = RendezVous.class)
    public Response getRendezVousById(@PathParam("id") int id) {
        RendezVous rendezVous = business.getRendezVousById(id);
        if (rendezVous != null) {
            return Response.ok(rendezVous).build();
        } else {
            return Response.status(404).entity("Rendez-vous non trouvé").build();
        }
    }

    // Afficher les rendez-vous d’un logement spécifique
    @GET
    @Path("/getByLogementRef/{ref}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Récupérer les rendez-vous pour une référence de logement", response = RendezVous.class, responseContainer = "List")
    public Response getByLogementReference(@PathParam("ref") int ref) {
        List<RendezVous> liste = business.getListeRendezVousByLogementReference(ref);
        return Response.ok(liste).build();
    }

    // Supprimer un rendez-vous
    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @ApiOperation(value = "Supprimer un rendez-vous par son ID", response = String.class)
    public Response deleteRendezVous(@PathParam("id") int id) {
        boolean deleted = business.deleteRendezVous(id);
        if (deleted) {
            return Response.ok("Rendez-vous supprimé avec succès").build();
        } else {
            return Response.status(404).entity("Rendez-vous introuvable").build();
        }
    }

    // Mettre à jour un rendez-vous
    @PATCH
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @ApiOperation(value = "Mettre à jour un rendez-vous existant", response = String.class)
    public Response updateRendezVous(@PathParam("id") int id, RendezVous rendezVous) {
        boolean updated = business.updateRendezVous(id, rendezVous);
        if (updated) {
            return Response.ok("Rendez-vous mis à jour avec succès").build();
        } else {
            return Response.status(404).entity("Rendez-vous non trouvé ou logement invalide").build();
        }
    }
}
