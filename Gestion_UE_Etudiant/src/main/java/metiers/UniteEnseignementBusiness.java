package metiers;


import entities.UniteEnseignement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UniteEnseignementBusiness {
    private static List<UniteEnseignement> unitesEnseignement;

    public UniteEnseignementBusiness() {
        unitesEnseignement = new ArrayList<UniteEnseignement>();
        // Initialisation avec quelques exemples
        unitesEnseignement.add(new UniteEnseignement(1, "Informatique", "Mme Maroua Douiri", 6, 1));
        unitesEnseignement.add(new UniteEnseignement(2, "Mathématiques", "Mme Ines ElMejid", 5, 1));
        unitesEnseignement.add(new UniteEnseignement(3, "Physique", "Mme Sarra Abidi", 4, 2));
        unitesEnseignement.add(new UniteEnseignement(4, "Infographie", "Mme Oumeima Ibnelfkih", 3, 1));
        unitesEnseignement.add(new UniteEnseignement(5, "Chimie", "M. Mohamed Amine Chebbi", 4, 2));
    }

    public UniteEnseignement getUEByCode(int code) {
        for (UniteEnseignement ue : unitesEnseignement) {
            if (ue.getCode() == code)
                return ue;
        }
        return null;
    }

    public boolean addUniteEnseignement(UniteEnseignement ue) {
        return unitesEnseignement.add(ue);
    }

    public List<UniteEnseignement> getUEByDomaine(String domaine) {
        List<UniteEnseignement> liste = new ArrayList<UniteEnseignement>();
        for (UniteEnseignement ue : unitesEnseignement) {
            if (ue.getDomaine().equalsIgnoreCase(domaine))
                liste.add(ue);
        }
        return liste;
    }

    public List<UniteEnseignement> getUEBySemestre(int semestre) {
        List<UniteEnseignement> liste = new ArrayList<UniteEnseignement>();
        for (UniteEnseignement ue : unitesEnseignement) {
            if (ue.getSemestre() == semestre)
                liste.add(ue);
        }
        return liste;
    }

    public boolean deleteUniteEnseignement(int code) {
        Iterator<UniteEnseignement> iterator = unitesEnseignement.iterator();
        while (iterator.hasNext()) {
            UniteEnseignement ue = iterator.next();
            if (ue.getCode() == code) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public boolean updateUniteEnseignement(int code, UniteEnseignement updatedUE) {
        int index = -1;
        for (int i = 0; i < unitesEnseignement.size(); i++) {
            if (unitesEnseignement.get(i).getCode() == code) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            unitesEnseignement.set(index, updatedUE);
            return true;
        } else {
            return false;
        }
    }

    public List<UniteEnseignement> getListeUE() {
        return unitesEnseignement;
    }

    public void setUnitesEnseignement(List<UniteEnseignement> unitesEnseignement) {
        this.unitesEnseignement = unitesEnseignement;
    }
}