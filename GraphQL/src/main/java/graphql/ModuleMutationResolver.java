package graphql;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import business.ModuleBusiness;
import entities.Module;
import entities.UniteEnseignement;

import java.util.List;

public class ModuleMutationResolver implements GraphQLRootResolver {

    private ModuleBusiness helper;

    public ModuleMutationResolver() {
        helper = new ModuleBusiness();
    }

    // Ajouter un module
    public boolean addModule(String matricule, String nom, int coefficient, int volumeHoraire,
                             Module.TypeModule type, UniteEnseignement ue) {
        Module module = new Module(matricule, nom, coefficient, volumeHoraire, type, ue);
        return helper.addModule(module);
    }

    // Mettre à jour un module
    public Module updateModule(String matricule, String nom, int coefficient, int volumeHoraire,
                               Module.TypeModule type, UniteEnseignement ue) {
        Module updated = new Module(matricule, nom, coefficient, volumeHoraire, type, ue);
        boolean success = helper.updateModule(matricule, updated);
        return success ? updated : null;
    }

    // Supprimer un module
    public boolean deleteModule(String matricule) {
        return helper.deleteModule(matricule);
    }
}