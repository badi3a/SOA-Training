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
    public Response createMB(Module unite) {
        if (helper.addModule(unite)) {
            return Response.status(201).entity("ok").build();
        } else {
            return Response.status(400).entity("error").build();
        }
    }

}