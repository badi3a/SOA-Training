package graphql;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import business.ModuleBusiness;
import entities.Module;
import entities.UniteEnseignement;

import java.util.List;

public class ModuleQueryResolver implements GraphQLRootResolver {

    private ModuleBusiness helper;

    public ModuleQueryResolver() {
        helper = new ModuleBusiness();
    }

    // Tous les modules
    public List<Module> allModules() {
        return helper.getAllModules();
    }

    // Module par matricule
    public Module getModuleByMatricule(String matricule) {
        return helper.getModuleByMatricule(matricule);
    }

    // Modules par type
    public List<Module> getModulesByType(Module.TypeModule type) {
        return helper.getModulesByType(type);
    }

    // Modules d'une UE spécifique
    public List<Module> getModulesByUE(UniteEnseignement ue) {
        return helper.getModulesByUE(ue);
    }
}