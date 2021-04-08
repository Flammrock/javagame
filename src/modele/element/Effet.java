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
    private double[] duréeDeLEffet = new double[2];
    private double[] forceAjoute = new double[3];   //tableau ou l'indice: 0=effetCourant, 1=effetInitial, 2=effetContinu
    private double[] agiliteAjoute = new double[3];
    private double[] pvAjoute = new double[3];
    private Lieu tpSalle;
    private double[] poidsAjoute = new double[3];

    public Effet(String Nom,String description, double duréeDeLEffet, double forceAjoute, double forceAjouteContinue, double agiliteAjoute, double agiliteAjouteContinue, double pvAjoute, double pvAjouteContinue, Lieu tpSalle, double poidsAjoute) {
        this.Nom = Nom;
        this.description = description;
        this.duréeDeLEffet[0] = duréeDeLEffet;
        this.duréeDeLEffet[1] = duréeDeLEffet;
        this.duréeDeLEffet[2] = 0;
        this.forceAjoute[0] = forceAjoute;
        this.forceAjoute[1] = forceAjoute;
        this.forceAjoute[2] = 0;
        this.agiliteAjoute[0] = agiliteAjoute;
        this.agiliteAjoute[1] = agiliteAjoute;
        this.agiliteAjoute[2] = 0;
        this.pvAjoute[0] = pvAjoute;
        this.pvAjoute[1] = pvAjoute;
        this.pvAjoute[2] = 0;
        this.tpSalle = tpSalle;
        this.poidsAjoute[0] = poidsAjoute;
        this.poidsAjoute[1] = poidsAjoute;
        this.poidsAjoute[2] = 0;
    }

    

    public String getNom() {
        return Nom;
    }

    public double getDuréeDeLEffet() {
        return duréeDeLEffet[0];
    }

    public double getForceAjoute() {
        return forceAjoute[0];
    }

    public double getAgiliteAjoute() {
        return agiliteAjoute[0];
    }

    public double getPvAjoute() {
        return pvAjoute[0];
    }

    public Lieu getTpSalle() {
        return tpSalle;
    }

    public void setTpSalle(Lieu tpSalle) {
        this.tpSalle = tpSalle;
    }

    public double getPoidsAjoute() {
        return poidsAjoute[0];
    }
    
    public boolean tourPasse(){
        if(duréeDeLEffet[0]==duréeDeLEffet[1] && duréeDeLEffet[0]==-1){//effet permanant 
            forceAjoute[0] = 0;
            agiliteAjoute[0] = 0;
            pvAjoute[0] = 0;
            tpSalle = null;
            poidsAjoute[0] = 0;
        }
        if(duréeDeLEffet[0]==duréeDeLEffet[1] && duréeDeLEffet[0]<-1){//effet instantané
            forceAjoute[0] = 0;
            agiliteAjoute[0] = 0;
            pvAjoute[0] = 0;
            tpSalle = null;
            poidsAjoute[0] = 0;
        }
        if(duréeDeLEffet[0]==duréeDeLEffet[1] && duréeDeLEffet[0]>-1){//effet a durée limité dans le temps
            forceAjoute[0] = 0;
            agiliteAjoute[0] = 0;
            pvAjoute[0] = 0;
            tpSalle = null;
            poidsAjoute[0] = 0;
        }
        if(duréeDeLEffet[0] == 0 && duréeDeLEffet[0]>-1){//fin de l'effet
            forceAjoute[0] = -forceAjoute[1];         //A finir
            agiliteAjoute[0] = -agiliteAjoute[1];
            pvAjoute[0] = -pvAjoute[1];
            tpSalle = null;
            poidsAjoute[0] = -poidsAjoute[1];
        }
        
        if(this.duréeDeLEffet[0]!=-1){
            this.duréeDeLEffet[0]--;
        }
        if(this.duréeDeLEffet[0]<-1){
            return false;
        }else{
            return true;
        }
    }
    
    
    
}
