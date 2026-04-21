package graphql;

// Import the business logic class responsible for managing "UniteEnseignement" data
import business.ModuleBusiness;
import business.UniteEnseignementBusiness;

// Import the GraphQL library class used to define the root of GraphQL queries
import com.coxautodev.graphql.tools.GraphQLRootResolver;

// Import the entity class that represents a Teaching Unit (Unité d’Enseignement)
import entities.UniteEnseignement;
import entities.Module;
import java.util.List;

/**
 * This class acts as the root resolver for GraphQL queries.
 * It tells GraphQL how to fetch data when a query is executed.
 *
 * In this example, it handles queries related to "UniteEnseignement".
 */
public class QueryResolver implements GraphQLRootResolver {

    // Attribute used to call the business logic layer (service) for data operations
    public UniteEnseignementBusiness helper;
    public ModuleBusiness helper2;


    /**
     * Constructor of the resolver.
     * It initializes the business helper that provides methods to retrieve UE data.
     */
    public QueryResolver(){
        helper = new UniteEnseignementBusiness();
    }

    /**
     * GraphQL Query: allUEs
     *
     * This method will be automatically exposed to the GraphQL schema as a query named "allUEs".
     *
     * When a client sends the GraphQL query:
     *
     *    query {
     *        allUEs {
     *            code
     *            domaine
     *        }
     *    }
     *
     * GraphQL will call this method to return a list of "UniteEnseignement" objects.
     *
     * @return List of all Teaching Units (Unité d’Enseignement)
     */
    public List<UniteEnseignement> allUEs(){
        // Delegates the request to the business layer method getListeUE()
        // which fetches all UE entities (e.g., from a database or a static list)
        return helper.getListeUE();
    }
    public UniteEnseignement getUeByCode (int code){
        return helper.getUEByCode(code);
    }
    public List<entities.Module> allModules(){
        return helper2.getAllModules();
    }

    public entities.Module getModuleByMatricule(String matricule){
        return helper2.getModuleByMatricule(matricule);
    }

    public List<Module> getModulesByType(Module.TypeModule type){
        return helper2.getModulesByType(type);
    }
}
