package entities;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "etudiants")
@XmlAccessorType(XmlAccessType.FIELD)
public class EtudiantList {
    @XmlElement(name = "etudiant")
    private List<Etudiant> etudiants;

    public EtudiantList() {}

    public EtudiantList(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    public List<Etudiant> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }
}