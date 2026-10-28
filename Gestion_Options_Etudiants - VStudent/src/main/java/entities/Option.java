package entities;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Option {
    private int codeOption;
    private String libelle;
    private String domaine;
    private String responsable;
    private int credits;
    private int semestre;
    private int capacite;

    public Option() {}

    public Option(int codeOption, String libelle, String domaine, String responsable, int credits, int semestre, int capacite) {
        this.codeOption = codeOption;
        this.libelle = libelle;
        this.domaine = domaine;
        this.responsable = responsable;
        this.credits = credits;
        this.semestre = semestre;
        this.capacite = capacite;
    }

    // Getters et Setters
    public int getCodeOption() {
        return codeOption;
    }

    public void setCodeOption(int codeOption) {
        this.codeOption = codeOption;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }
}