package restApi;

import entities.Logement;
import metiers.LogementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/logement")
public class LogementRestApi {
    static LogementBusiness LBhelp = new LogementBusiness();


    @GET
    @Path("/getLogements")
    @Produces(MediaType.APPLICATION_JSON)
    //@produces(MediaType.APPLICATION_XML)
    public Response  getAll(){
        return Response
                .status(200)
                .entity(LBhelp.getLogements())
                .build();
    }

    @GET
    @Path("/by-ref/{ref}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response  getLogByRef(@PathParam(value = "ref") int ref){
        return Response
        .status(200)
        .entity(LBhelp. getLogementsByReference(ref))
        .build();
    }
//querryparam
@GET
@Produces(MediaType.APPLICATION_JSON)
public Response getLogementsByDeleguation(@QueryParam(value = "delg") String delg) {
    return Response
            .status(200)
            .entity(LBhelp. getLogementsByDeleguation(delg))
            .build();
}
//pathparam
//    @GET
//    @Path("/by-del/{delegation}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getLogementsByDeleguation(@PathParam(value ="delegation") String delegation) {
//        return Response
//                .status(200)
//                .entity(LBhelp. getLogementsByDeleguation(delegation))
//                .build();
//    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addLogement (Logement l) {
        LBhelp.addLogement(l);
        return Response
                .status(201)
                .entity("logement added successfully")
                .build();
    }



    @GET
    @Path("/listLogby-ref/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogementsListeByref(@PathParam("reference") int reference) {
        return Response
                .status(200)
                .entity(LBhelp.getLogementsListeByref(reference))
                .build();
    }

    @DELETE
    @Path("/delete/{reference}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteLogementByReference(@PathParam("reference") int reference) {
        boolean isDeleted = LBhelp.deleteLogement(reference);

        if (isDeleted) {
            return Response
                    .status(200)
                    .entity("Logement supprimé avec succès")
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Aucun logement trouvé ")
                    .build();
        }
    }

    @PUT
    @Path("/update/{reference}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLogementByReference(@PathParam("reference") int reference, Logement logement) {
        boolean updated = LBhelp.updateLogement(reference, logement);

        if (updated) {
            return Response
                    .status(200)
                    .entity(logement)
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Aucun logement trouvé")
                    .build();
        }
    }

    @PUT
    @Path("/setlogements")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response setAllLogements(List<Logement> logements) {
        LBhelp.setLogements(logements);
        return Response
                .status(200)
                .entity(logements)
                .build();
    }


}
