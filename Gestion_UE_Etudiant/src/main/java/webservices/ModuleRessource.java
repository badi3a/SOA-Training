package webservices;

import entities.Module;
import metiers.ModuleBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/modules")
public class ModuleRessource {

    private static final ModuleBusiness business = new ModuleBusiness();

    // 1) CREATE: POST /api/modules
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Module m) {
        if (business.addModule(m))
            return Response.status(Response.Status.CREATED).build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    // 2) READ ALL: GET /api/modules
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok(business.getAllModules()).build();
    }

    // 6) GET /api/modules/UE?codeUE=1 (or /api/modules/ue?codeUE=1)
    @GET
    @Path("{ue: [uU][eE]}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByUE(@QueryParam("codeUE") Integer codeUE) {
        if (codeUE != null) {
            entities.UniteEnseignement ue = new entities.UniteEnseignement();
            ue.setCode(codeUE);
            java.util.List<Module> list = business.getModulesByUE(ue);
            if (list != null && !list.isEmpty()) {
                return Response.ok(list).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    // READ ONE: GET /api/modules/M101
    @GET
    @Path("/{matricule}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOne(@PathParam("matricule") String matricule) {
        Module m = business.getModuleByMatricule(matricule);
        return m == null ? Response.status(Response.Status.NOT_FOUND).build()
                : Response.ok(m).build();
    }

    // UPDATE: PUT /api/modules/M101
    @PUT
    @Path("/{matricule}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("matricule") String matricule, Module m) {
        if (business.updateModule(matricule, m))
            return Response.ok().build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    // DELETE: DELETE /api/modules/M101
    @DELETE
    @Path("/{matricule}")
    public Response delete(@PathParam("matricule") String matricule) {
        if (business.deleteModule(matricule))
            return Response.ok().build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}