/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.element;

/**
 *
 * @author Utilisateur
 */
public class Effet extends Element {
    private String Nom;
    private double duréeDeLEffet;
    private double forceAjoute;
    private double forceAjouteContinue;
    private double agiliteAjoute;
    private double agiliteAjouteContinue;
    private double pvAjoute;
    private double pvAjouteContinue;
    private Lieu tpSalle;
    private double poidsAjoute;

    public Effet(String Nom,String description, double duréeDeLEffet, double forceAjoute, double forceAjouteContinue, double agiliteAjoute, double agiliteAjouteContinue, double pvAjoute, double pvAjouteContinue, Lieu tpSalle, double poidsAjoute) {
        this.Nom = Nom;
        this.description = description;
        this.duréeDeLEffet = duréeDeLEffet;
        this.forceAjoute = forceAjoute;
        this.forceAjouteContinue = forceAjouteContinue;
        this.agiliteAjoute = agiliteAjoute;
        this.agiliteAjouteContinue = agiliteAjouteContinue;
        this.pvAjoute = pvAjoute;
        this.pvAjouteContinue = pvAjouteContinue;
        this.tpSalle = tpSalle;
        this.poidsAjoute = poidsAjoute;
    }

    

    public String getNom() {
        return Nom;
    }

    public double getDuréeDeLEffet() {
        return duréeDeLEffet;
    }

    public double getForceAjoute() {
        return forceAjoute;
    }

    public double getForceAjouteContinue() {
        return forceAjouteContinue;
    }

    public double getAgiliteAjoute() {
        return agiliteAjoute;
    }

    public double getAgiliteAjouteContinue() {
        return agiliteAjouteContinue;
    }

    public double getPvAjoute() {
        return pvAjoute;
    }

    public double getPvAjouteContinue() {
        return pvAjouteContinue;
    }

    public Lieu getTpSalle() {
        return tpSalle;
    }

    public void setTpSalle(Lieu tpSalle) {
        this.tpSalle = tpSalle;
    }

    public double getPoidsAjoute() {
        return poidsAjoute;
    }
    
    public boolean tourPasser(){
        if(this.duréeDeLEffet!=-1){
            this.duréeDeLEffet--;
        }
        if(this.duréeDeLEffet<-1){
            return false;
        }else{
            return true;
        }
    }
}
