package ressources;

import entities.Etudiant;
import metiers.EtudiantBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.util.List;

@Path("/etudiants")
public class EtudiantRessource {

    public static EtudiantBusiness eb = new EtudiantBusiness();

    //    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<Etudiant> getAllStudents() {
//        return eb.getAllEtudiants();
//
//    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllStudents() {
        return Response.ok(eb.getAllEtudiants()).build();

    }

//    @POST
//    @Consumes(MediaType.APPLICATION_XML)
//    public void createStudent(Etudiant e) {
//        eb.addEtudiant(e);
//    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response createStudent(Etudiant e) {
        if (eb.addEtudiant(e)){
            return Response.status(Response.Status.CREATED).build();

        }
        else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

//    @DELETE
//    @Path("/{id}")
//    public void deleteStudent (@PathParam("id") String code)   {
//        eb.deleteEtudiant(code);
//    }

    @DELETE
    @Path("/{id}")
    public Response deleteStudent (@PathParam("id") String code)   {
        if (eb.deleteEtudiant(code)) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

//    @PUT
//    @Consumes (MediaType.APPLICATION_XML)
//    @Path("/update/{id}")
//    public void updateStudent (@PathParam("id") String code, Etudiant e ){
//        eb.updateEtudiant(code, e);
//    }

    @PUT
    @Consumes (MediaType.APPLICATION_XML)
    @Path("/update/{id}")
    public Response updateStudent (@PathParam("id") String code, Etudiant e ){
        if (eb.updateEtudiant(code, e)){
            return Response.ok().build();
        }
            return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/search")
    public  Response getAllStudentsByOption (@QueryParam("code") int code){
        List<Etudiant> el = eb.getEtudiantsByOption(code);
        if (!el.isEmpty()){
            return Response.ok(el).build();
        }
        else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

}
