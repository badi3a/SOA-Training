package webservices;

import entities.Logement;
import metiers.LogementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/logement")
public class LogementRessources {
    static LogementBusiness help = new LogementBusiness();
    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response  getAll(){
        return Response.
                status(200).header("Access-Control-Allow-Origin", "*").
                entity(help.getLogements()).
                build();
    }

    // New method to get a Logement by reference
    @GET
    @Path("/get/{reference}")  // URL: /logement/get/{reference}
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogementByReference(@PathParam("reference") int reference) {
        Logement logement = help.getLogementsByReference(reference);

        if (logement != null) {
            return Response.status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity(logement)
                    .build();
        } else {
            return Response.status(404)  // Not Found if logement doesn't exist
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("Logement not found with reference: " + reference)
                    .build();
        }
    }

    // New method to add a logement
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON) // Accepts JSON input
    @Produces(MediaType.APPLICATION_JSON)
    public Response addLogement(Logement logement) {
        if (logement == null) {
            return Response.status(400) // Bad Request
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("Invalid logement data")
                    .build();
        }

        boolean added = help.addLogement(logement);
        if (added) {
            return Response.status(201) // Created
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("Logement added successfully")
                    .build();
        } else {
            return Response.status(500) // Internal Server Error
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("Failed to add logement")
                    .build();
        }
    }

    // New endpoint for getting logements by delegation
    @GET
    @Path("/getByDelegation/{delegation}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogementsByDelegation(@PathParam("delegation") String delegation) {
        List<Logement> logements = help.getLogementsByDeleguation(delegation);
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(logements)
                .build();
    }

    // New endpoint for getting logements by reference
    @GET
    @Path("/getByReference/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogementsListeByRef(@PathParam("reference") int reference) {
        List<Logement> logements = help.getLogementsListeByref(reference);
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(logements)
                .build();
    }

    // New endpoint for deleting a logement by reference
    @DELETE
    @Path("/delete/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteLogement(@PathParam("reference") int reference) {
        boolean deleted = help.deleteLogement(reference);
        if (deleted) {
            return Response
                    .status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("{\"message\": \"Logement deleted successfully\"}")
                    .build();
        } else {
            return Response
                    .status(404)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("{\"error\": \"Logement not found\"}")
                    .build();
        }
    }

    // New endpoint for updating a logement by reference
    @PUT
    @Path("/update/{reference}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLogement(@PathParam("reference") int reference, Logement logement) {
        boolean updated = help.updateLogement(reference, logement);
        if (updated) {
            return Response
                    .status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("{\"message\": \"Logement updated successfully\"}")
                    .build();
        } else {
            return Response
                    .status(404)
                    .header("Access-Control-Allow-Origin", "*")
                    .entity("{\"error\": \"Logement not found\"}")
                    .build();
        }
    }
}


