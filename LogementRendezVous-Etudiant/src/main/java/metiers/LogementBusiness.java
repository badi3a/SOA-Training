package metiers;

import entities.Logement;
import entities.RendezVous;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LogementBusiness {
    private List<Logement> logements ;

    public LogementBusiness() {
        logements=new ArrayList<Logement>();
        logements.add(new Logement(1,"27, Rue des roses", "El ghazela","Ariana","Studio","cuisine equipee",300f));
        logements.add(new Logement(5,"58, Rue des roses", "El ghazela","Ariana","EtageVilla","cuisine equipee",450f));
        logements.add(new Logement(2,"201, R�sidence Omrane4", "Riadh El Andalous","Ariana","EtageVilla","chauffage central, ascenseur, climatisation",700f));
        logements.add(new Logement(3,"540, R�sidence Les Tulipes", "El Aouina","Ariana","Appartement","S+2, chauffage central, ascenseur, climatisation",500f));
        logements.add(new Logement(4,"78, Rue des Oranges", "Bardo","Tunis","EtageVilla","chauffage central, ascenseur, climatisation",400f));

    }


    public Logement getLogementsByReference(int reference){

        for (Logement l:logements){
            if(l.getReference()==reference)
                return l;
        }
        return null;
    }
    public boolean addLogement(Logement logement){

       return logements.add(logement);
    }
    public List<Logement> getLogementsByDeleguation(String deleguation){
        List<Logement> liste=new ArrayList<Logement>();
        for (Logement l:logements){
            if(l.getDelegation().equals(deleguation))
                liste.add(l);
        }
        return liste;
    }
    public List<Logement> getLogementsListeByref(int reference){
        List<Logement> liste=new ArrayList<Logement>();
        for (Logement l:logements){
            if(l.getReference()== reference)
                liste.add(l);
        }
        return liste;
    }
    public boolean deleteLogement(int reference){
        Iterator<Logement> iterator=logements.iterator();
        while(iterator.hasNext()){
            Logement l=iterator.next();
            if(l.getReference()==reference){
                iterator.remove();
                return true;
            }
        }
        return false;
    }
    public boolean updateLogement (int reference, Logement logement){
        int index = -1;
        for (int i = 0; i < logements.size(); i++) {
            if (logements.get(i).getReference() == reference) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            logements.set(index, logement);
            return true;
        } else {
            return false;
        }
    }
    public List<Logement> getLogements() {
        return logements;
    }

    public void setLogements(List<Logement> logements) {
        this.logements = logements;
    }

}
