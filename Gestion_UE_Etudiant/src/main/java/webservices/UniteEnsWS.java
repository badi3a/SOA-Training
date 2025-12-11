package webservices;

import entities.UniteEnseignement;
import filtres.Secured;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@Path("/ue")
public class UniteEnsWS {
    static UniteEnseignementBusiness helper=
            new UniteEnseignementBusiness();
    @Secured
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        return Response
                .status(200)
                .entity(helper.getListeUE())
                .build();
    }
    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addUE(UniteEnseignement ue){
        if(helper.addUniteEnseignement(ue)==true){
            return Response.status(201).
                    entity("object added").build();
        }else return
                Response.status(400).entity("object not added").build();
    }

    @Path("/delete/{id}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteUE(@PathParam("id") int code){
        if(helper.deleteUniteEnseignement(code)==true){
            return Response
                    .status(200)
                    .entity("object deleted").build();
        }else return Response.status(400)
                .entity("object not FOUND, please").build();
    }

    @Path("/update/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateUE(UniteEnseignement ue,@PathParam("id") int code){
        if(helper.updateUniteEnseignement(code,ue)==true){
            return Response.status(200).
                    entity("object updated").build();
        }else return
                Response.status(400).entity("object not updated").build();
    }
}
//marwa was here
