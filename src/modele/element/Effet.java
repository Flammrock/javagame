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
    private double duréeDeLEffetintial;
    private double forceAjoute;
    private double agiliteAjoute;
    private double pvAjoute;
    private Lieu tpSalle;
    private double poidsAjoute;

    public Effet(String Nom,String description, double duréeDeLEffet, double forceAjoute, double forceAjouteContinue, double agiliteAjoute, double agiliteAjouteContinue, double pvAjoute, double pvAjouteContinue, Lieu tpSalle, double poidsAjoute) {
        this.Nom = Nom;
        this.description = description;
        this.duréeDeLEffet = duréeDeLEffet;
        this.duréeDeLEffetintial = duréeDeLEffet;
        this.forceAjoute = forceAjoute;
        this.agiliteAjoute = agiliteAjoute;
        this.pvAjoute = pvAjoute;
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

    public double getAgiliteAjoute() {
        return agiliteAjoute;
    }

    public double getPvAjoute() {
        return pvAjoute;
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
    
    public boolean tourPasse(){
        if(duréeDeLEffet==duréeDeLEffetintial && duréeDeLEffet==-1);//effet permanant 
        if(duréeDeLEffet==duréeDeLEffetintial && duréeDeLEffet<-1){//effet instantané
            forceAjoute = 0;
            agiliteAjoute = 0;
            pvAjoute = 0;
            tpSalle = null;
            poidsAjoute = 0;
        }
        if(duréeDeLEffet==duréeDeLEffetintial && duréeDeLEffet>-1){//effet a durée limité dans le temps
            forceAjoute = 0;
            agiliteAjoute = 0;
            pvAjoute = 0;
            tpSalle = null;
            poidsAjoute = 0;
        }
        if(duréeDeLEffet == 0 && duréeDeLEffet>-1){//fin de l'effet
            forceAjoute = -forceAjoute;         //A finir
            agiliteAjoute = -agiliteAjoute;
            pvAjoute = -pvAjoute;
            tpSalle = null;
            poidsAjoute = -poidsAjoute;
        }
        
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
