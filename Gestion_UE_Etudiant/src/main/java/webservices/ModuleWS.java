package webservices;

import entities.Module;
import filtres.Secured;
import metiers.ModuleBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/module")
public class ModuleWS {
    ModuleBusiness helper = new ModuleBusiness();

    @Path("/list")
    @Secured
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        return Response.status(200).entity(helper.getAllModules()).build();
    }

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addModule(Module module){
        if(helper.addModule(module)){
            return Response
                    .status(201)
                    .entity("Module added").build();
        }else return Response
                .status(400)
                .entity("Module not added")
                .build();

    }
}
