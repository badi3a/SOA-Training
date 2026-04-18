package webservices;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/UE")
public class UERessources {

    private UniteEnseignementBusiness ueBusiness = new UniteEnseignementBusiness();

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addUE(UniteEnseignement ue) {
        boolean added = ueBusiness.addUniteEnseignement(ue);
        if (added) {
            return Response.status(Response.Status.CREATED)
                    .entity("Unité d'enseignement ajoutée avec succès.")
                    .build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity("Ajout impossible.")
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUEs(@QueryParam("semestre") Integer semestre,
                           @QueryParam("code") Integer code) {

        if (code != null) {
            UniteEnseignement ue = ueBusiness.getUEByCode(code);
            if (ue != null) {
                return Response.status(Response.Status.OK)
                        .entity(ue)
                        .build();
            }
            return Response.status(Response.Status.NOT_FOUND)
                           .build();
        }

        if (semestre != null) {
            List<UniteEnseignement> liste = ueBusiness.getUEBySemestre(semestre);
            return Response.status(Response.Status.OK)
                           .entity(liste)
                            .build();
        }

        return Response.status(Response.Status.OK)
                .entity(ueBusiness.getListeUE())
                .build();
    }

    @DELETE
    @Path("/{code}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteUE(@PathParam("code") int code) {
        boolean deleted = ueBusiness.deleteUniteEnseignement(code);
        if (deleted) {
            return Response.status(Response.Status.OK)
                    .entity("Unité d'enseignement supprimée avec succès.")
                    .build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity("Aucune unité d'enseignement trouvée avec ce code.")
                .build();
    }

    @PUT
    @Path("/{code}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateUE(@PathParam("code") int code, UniteEnseignement ue) {
        boolean updated = ueBusiness.updateUniteEnseignement(code, ue);
        if (updated) {
            return Response.status(Response.Status.OK)
                    .entity("Unité d'enseignement modifiée avec succès.")
                    .build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity("Modification impossible : unité d'enseignement introuvable.")
                .build();
    }
}