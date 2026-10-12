package webservices;

import entities.Module;
import metiers.ModuleBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/MB")
public class ModuleRestAPI {

    ModuleBusiness helper = new ModuleBusiness();


    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getlistModule() {
        return Response.status(200).entity(helper.getAllModules()).build();
    }

    @Path("/new")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createMB(Module module) {
        if (helper.addModule(module)) {
            return Response.status(201).entity("ok").build();
        } else {
            return Response.status(400).entity("error").build();
        }
    }

    @Path("/type")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getModulesByType(@QueryParam("type") String type) {
        try {
            Module.TypeModule t = Module.TypeModule.valueOf(type.toUpperCase());
            return Response.ok(helper.getModulesByType(t)).build();
        } catch (IllegalArgumentException | NullPointerException e) {
            return Response.status(400).entity("error").type(MediaType.TEXT_PLAIN).build();
        }
    }

    @Path("/ue")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getModulesByUE(@QueryParam("code") int code) {
        return Response.ok(helper.getModulesByUE(code)).build();
    }


    @Path("/{matricule}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModule(@PathParam("matricule") String matricule) {
        Module m = helper.getModuleByMatricule(matricule);
        if (m == null) {
            return Response.status(404).entity("Module introuvable").type(MediaType.TEXT_PLAIN).build();
        }
        return Response.status(200).entity(m).build();
    }

    @Path("/update")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateMB(@QueryParam("matricule") String matricule, Module module) {
        if (helper.updateModule(matricule, module)) {
            return Response.status(200).entity("ok").build();
        } else {
            return Response.status(400).entity("error").build();
        }
    }

    @Path("/delete")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteMB(@QueryParam("matricule") String matricule) {
        if (helper.deleteModule(matricule)) {
            return Response.status(200).entity("ok").build();
        } else {
            return Response.status(400).entity("error").build();
        }
    }
}