package restApi;

import entities.RendezVous;
import metiers.LogementBusiness;
import metiers.RendezVousBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/rdv")
public class RendezVousRestapi {
  static  RendezVousBusiness RVhelp = new RendezVousBusiness();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addRendezVous(RendezVous rendezVous) {
        RVhelp.addRendezVous(rendezVous);
            return Response
                    .status(201)
                    .entity("rdv added successfully")
                    .build();

    }

    @GET
    @Path("/getrdv")
    @Produces(MediaType.APPLICATION_JSON)
    public Response  getAll(){
        return Response
                .status(200)
                .entity(RVhelp. getListeRendezVous())
                .build();
    }

    @PUT
    @Path("/setall")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response setAllRendezVous(List<RendezVous> liste) {
        RVhelp.setListeRendezVous(liste);
        return Response
                .status(200)
                .entity("Liste des rendez-vous mise à jour.")
                .build();
    }

    @GET
    @Path("/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRendezVousByLogement(@PathParam("reference") int reference) {
        List<RendezVous> liste = RVhelp.getListeRendezVousByLogementReference(reference);

        return Response
                .status(200)
                .entity(liste)
                .build();
    }

    @GET
    @Path("byid/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRendezVousById(@PathParam("id") int id) {
        return Response
                .status(200)
                .entity(RVhelp.getRendezVousById(id))
                .build();
    }
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteRendezVous(@PathParam("id") int id) {
        RVhelp.deleteRendezVous(id);
            return Response.status(200)
                    .entity("Rendez-vous supprimé avec succès.")
                    .build();


    }

    @PUT
    @Path("update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateRendezVous(@PathParam("id") int id, RendezVous updatedRendezVous) {
     RVhelp.updateRendezVous(id, updatedRendezVous);
            return Response
                    .status(200)
                    .entity("Rendez-vous mis à jour avec succès")
                    .build();

    }


}
