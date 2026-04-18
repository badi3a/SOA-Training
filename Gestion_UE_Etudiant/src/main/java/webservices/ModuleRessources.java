package webservices;

import entities.Module;
import metiers.ModuleBusiness;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/modules")
@Tag(name = "Module API", description = "Gestion des modules pédagogiques")
public class ModuleRessources {

    private ModuleBusiness moduleBusiness = new ModuleBusiness();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    /// swagger
    @Operation(summary = "Ajouter un module", description = "Ajoute un nouveau module pédagogique")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Module ajouté avec succès",
                    content = @Content(schema = @Schema(implementation = Module.class))),
            @ApiResponse(responseCode = "404", description = "Ajout impossible")
    })
    public Response addModule(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Données du module à ajouter",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Module.class))
            )
            Module module) {

        boolean added = moduleBusiness.addModule(module);

        if (added) {
            return Response.status(Response.Status.CREATED)
                    .entity(module)
                    .build();
        }

        return Response.status(Response.Status.NOT_FOUND)
                .entity("Ajout impossible : module invalide ou UE inexistante.")
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Récupérer tous les modules", description = "Retourne la liste complète des modules")
    @ApiResponse(responseCode = "200", description = "Liste des modules récupérée avec succès",
            content = @Content(schema = @Schema(implementation = Module.class)))
    public Response getAllModules() {
        List<Module> modules = moduleBusiness.getAllModules();

        return Response.status(Response.Status.OK)
                .entity(modules)
                .build();
    }

    @GET
    @Path("/{matricule}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Récupérer un module par matricule", description = "Retourne un module spécifique à partir de sa matricule")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Module trouvé",
                    content = @Content(schema = @Schema(implementation = Module.class))),
            @ApiResponse(responseCode = "404", description = "Module introuvable")
    })
    public Response getModuleByMatricule(
            @Parameter(description = "Matricule du module", required = true, example = "M101")
            @PathParam("matricule") String matricule) {

        Module module = moduleBusiness.getModuleByMatricule(matricule);

        if (module != null) {
            return Response.status(Response.Status.OK)
                    .entity(module)
                    .build();
        }

        return Response.status(Response.Status.NOT_FOUND)
                .entity("Aucun module trouvé avec cette matricule.")
                .build();
    }

    @DELETE
    @Path("/{matricule}")
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(summary = "Supprimer un module", description = "Supprime un module à partir de sa matricule")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Module supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Module introuvable")
    })
    public Response deleteModule(
            @Parameter(description = "Matricule du module à supprimer", required = true, example = "M101")
            @PathParam("matricule") String matricule) {

        boolean deleted = moduleBusiness.deleteModule(matricule);

        if (deleted) {
            return Response.status(Response.Status.OK)
                    .entity("Module supprimé avec succès.")
                    .build();
        }

        return Response.status(Response.Status.NOT_FOUND)
                .entity("Aucun module trouvé avec cette matricule.")
                .build();
    }

    @PUT
    @Path("/{matricule}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(summary = "Modifier un module", description = "Met à jour les informations d'un module existant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Module modifié avec succès"),
            @ApiResponse(responseCode = "404", description = "Module introuvable ou UE invalide")
    })
    public Response updateModule(
            @Parameter(description = "Matricule du module à modifier", required = true, example = "M102")
            @PathParam("matricule") String matricule,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Nouvelles données du module",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Module.class))
            )
            Module module) {

        boolean updated = moduleBusiness.updateModule(matricule, module);

        if (updated) {
            return Response.status(Response.Status.OK)
                    .entity("Module modifié avec succès.")
                    .build();
        }

        return Response.status(Response.Status.NOT_FOUND)
                .entity("Modification impossible : module introuvable ou UE invalide.")
                .build();
    }

    @GET
    @Path("/UE")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Récupérer les modules d'une UE", description = "Retourne tous les modules associés à une unité d'enseignement donnée")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des modules trouvée",
                    content = @Content(schema = @Schema(implementation = Module.class))),
            @ApiResponse(responseCode = "404", description = "Aucun module trouvé pour cette UE")
    })
    public Response getModulesByUE(
            @Parameter(description = "Code de l'unité d'enseignement", required = true, example = "1")
            @QueryParam("codeUE") int codeUE) {

        List<Module> modules = moduleBusiness.getModulesByCodeUE(codeUE);

        if (modules != null && !modules.isEmpty()) {
            return Response.status(Response.Status.OK)
                    .entity(modules)
                    .build();
        }

        return Response.status(Response.Status.NOT_FOUND)
                .entity("Aucun module trouvé pour cette unité d'enseignement.")
                .build();
    }
}