package WebServices;

import entities.Logement;
import jdk.dynalink.beans.StaticClass;
import metiers.LogementBusiness;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/logement")
public class LogementAPI {
     LogementBusiness logementBusiness = new LogementBusiness();

    @GET
    @Path("/getall")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllLogement() {
        try {
            List<Logement> logements = logementBusiness.getLogements();

            return Response.ok(logements).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving logements: " + e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/addlogement")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addLogement( Logement logement) {

        Boolean save = logementBusiness.addLogement(logement);
        return Response.ok("added success").build();
    }


    @GET
    @Path("/getlogementbyreference/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogementByReference(@PathParam("reference") int reference) {
        try {
            Logement logement = logementBusiness.getLogementsByReference(reference);
            if (logement == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Logement not found for reference: " + reference)
                        .build();
            }
            return Response.ok(logement).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving logement: " + e.getMessage())
                    .build();
        }


    }

    @DELETE
    @Path("/deletelogement/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteLogement(@PathParam("reference") int reference) {
        try {

            Logement logement = logementBusiness.getLogementsByReference(reference);
            if (logement == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Logement not found for reference: " + reference)
                        .build();
            }


            boolean deletionSuccess = logementBusiness.deleteLogement(reference);

            if (!deletionSuccess) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Failed to delete logement with reference: " + reference)
                        .build();
            }

            return Response.ok("Logement with reference " + reference + " was successfully deleted")
                    .build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error deleting logement: " + e.getMessage())
                    .build();
        }
    }

    @Path("updatelogement/{reference}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLogement(@PathParam("reference") int reference, Logement logement){
        try {
            Logement search = logementBusiness.getLogementsByReference(reference);
            if (search == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Logement not found for reference: " + reference)
                        .build();
            }
            logementBusiness.updateLogement(reference,logement);
            return Response.ok("logement updated successfully:"+ logement).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving logement: " + e.getMessage())
                    .build();
        }
    }

}





