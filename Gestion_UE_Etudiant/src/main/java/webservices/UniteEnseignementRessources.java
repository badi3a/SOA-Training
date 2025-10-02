package webservices;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/unites")
public class UniteEnseignementRessources {

    private UniteEnseignementBusiness ueBusiness = new UniteEnseignementBusiness();

    // Get all units
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUE() {
        try {
            List<UniteEnseignement> liste = ueBusiness.getListeUE();
            return Response.status(Response.Status.OK)
                    .entity(liste)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error retrieving units: " + e.getMessage() + "\"}")
                    .build();
        }
    }

    // Get unit by code
    @GET
    @Path("/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUEByCode(@PathParam("code") int code) {
        try {
            UniteEnseignement ue = ueBusiness.getUEByCode(code);
            if (ue != null) {
                return Response.status(Response.Status.OK)
                        .entity(ue)
                        .build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\": \"Unité d'enseignement not found with code: " + code + "\"}")
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error retrieving unit: " + e.getMessage() + "\"}")
                    .build();
        }
    }

    // Get units by domain
    @GET
    @Path("/domaine/{domaine}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUEByDomaine(@PathParam("domaine") String domaine) {
        try {
            List<UniteEnseignement> liste = ueBusiness.getUEByDomaine(domaine);
            return Response.status(Response.Status.OK)
                    .entity(liste)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error retrieving units by domain: " + e.getMessage() + "\"}")
                    .build();
        }
    }

    // Get units by semester
    @GET
    @Path("/semestre/{semestre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUEBySemestre(@PathParam("semestre") int semestre) {
        try {
            List<UniteEnseignement> liste = ueBusiness.getUEBySemestre(semestre);
            return Response.status(Response.Status.OK)
                    .entity(liste)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error retrieving units by semester: " + e.getMessage() + "\"}")
                    .build();
        }
    }

    // Add a new unit - FIXED VERSION
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON) // Changed from TEXT_PLAIN to APPLICATION_JSON
    public Response addUE(UniteEnseignement ue) {
        try {
            System.out.println("Received UE for creation: " + ue);

            // Validate input
            if (ue == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\": \"Request body is null\"}")
                        .build();
            }

            // Check if required fields are present
            if (ue.getCode() == 0 || ue.getDomaine() == null || ue.getDomaine().trim().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\": \"Code and domaine are required fields\"}")
                        .build();
            }

            // Check if code already exists
            UniteEnseignement existingUE = ueBusiness.getUEByCode(ue.getCode());
            if (existingUE != null) {
                return Response.status(Response.Status.CONFLICT)
                        .entity("{\"error\": \"Unité d'enseignement with code \" + ue.getCode() + \" already exists\"}")
                        .build();
            }

            // Add the new unit
            boolean added = ueBusiness.addUniteEnseignement(ue);
            if (added) {
                // Return the created object with success message
                String successMessage = "{\"message\": \"Unité d'enseignement added successfully\", \"code\": " + ue.getCode() + "}";
                return Response.status(Response.Status.CREATED)
                        .entity(successMessage)
                        .build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("{\"error\": \"Failed to add unité d'enseignement\"}")
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Server error: " + e.getMessage() + "\"}")
                    .build();
        }
    }

    // Update a unit - FIXED VERSION
    @PUT
    @Path("/{code}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON) // Changed from TEXT_PLAIN to APPLICATION_JSON
    public Response updateUE(@PathParam("code") int code, UniteEnseignement updatedUE) {
        try {
            System.out.println("Updating UE with code: " + code);

            // Validate input
            if (updatedUE == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\": \"Request body is null\"}")
                        .build();
            }

            // Ensure the code in path matches the object code
            if (updatedUE.getCode() != code) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\": \"Code in path does not match code in request body\"}")
                        .build();
            }

            boolean updated = ueBusiness.updateUniteEnseignement(code, updatedUE);
            if (updated) {
                String successMessage = "{\"message\": \"Unité d'enseignement updated successfully\", \"code\": " + code + "}";
                return Response.status(Response.Status.OK)
                        .entity(successMessage)
                        .build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\": \"Unité d'enseignement not found with code: " + code + "\"}")
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Server error: " + e.getMessage() + "\"}")
                    .build();
        }
    }

    // Delete a unit - FIXED VERSION
    @DELETE
    @Path("/{code}")
    @Produces(MediaType.APPLICATION_JSON) // Changed from TEXT_PLAIN to APPLICATION_JSON
    public Response deleteUE(@PathParam("code") int code) {
        try {
            boolean deleted = ueBusiness.deleteUniteEnseignement(code);
            if (deleted) {
                String successMessage = "{\"message\": \"Unité d'enseignement deleted successfully\", \"code\": " + code + "}";
                return Response.status(Response.Status.OK)
                        .entity(successMessage)
                        .build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\": \"Unité d'enseignement not found with code: " + code + "\"}")
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Server error: " + e.getMessage() + "\"}")
                    .build();
        }
    }
}