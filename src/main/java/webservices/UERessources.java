package webservices;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/ue")
public class UERessources {

    static UniteEnseignementBusiness helper = new UniteEnseignementBusiness();

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUEs() {
        return Response
                .status(Response.Status.OK)
                .entity(helper.getListeUE())
                .build();
    }

    @POST
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addUe(UniteEnseignement ue) {
        if (helper.addUniteEnseignement(ue)) {
            return Response
                    .status(Response.Status.CREATED)
                    .entity("object created")
                    .build();
        } else {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("object not created")
                    .build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteUe(@PathParam("id") int id) {
        if (helper.deleteUniteEnseignement(id)) {
            return Response
                    .status(Response.Status.OK)
                    .entity("object deleted")
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("object not found")
                    .build();
        }
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateUe(@PathParam("id") int id, UniteEnseignement ue) {

        if (helper.updateUniteEnseignement(id, ue)) {
            return Response
                    .status(Response.Status.OK)
                    .entity("object updated")
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("object not found")
                    .build();
        }
    }
}
