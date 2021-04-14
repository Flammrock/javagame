/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.element;

import canvas.Canvas;
import canvas.Drawable;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Utilisateur
 */
public class Effet extends Element {
    
    private String Nom;
    private boolean permanant;
    private boolean consomable;
    private double duréeDeLEffet;
    private double forceAjoute;
    private double agiliteAjoute ;
    private double pvAjoute;
    private double pvMaxAjoute;
    private double poidsAjoute;
    private double armureAjoute;
    private Lieu tpSalle;
    private ArrayList<Property> listproperties; 

    public Effet(String Nom,String description, boolean permanant, boolean consomable, double duréeDeLEffet, double forceAjoute, double agiliteAjoute, double pvAjoute, double pvMaxAjoute, double poidsAjoute, double armureAjoute, Lieu tpSalle) {
        this.Nom = Nom;
        this.description = description;
        this.permanant = permanant;
        this.consomable = consomable;
        this.duréeDeLEffet = duréeDeLEffet;
        this.forceAjoute = forceAjoute;
        this.agiliteAjoute = agiliteAjoute;
        this.pvAjoute = pvAjoute;
        this.pvMaxAjoute = pvMaxAjoute;
        this.poidsAjoute = poidsAjoute;
        this.armureAjoute = armureAjoute;
        this.tpSalle = tpSalle;
    }

    public Effet(String Nom,String description , boolean permanant, boolean consomable) {
        this.Nom = Nom;
        this.description = description;
        this.permanant = permanant;
        this.consomable = consomable;
    }
    
    public void addProperty(String nom,double valeur){
        listproperties.add(new Property(nom,valeur));
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
    
    public double getPvMaxAjoute() {
        return pvMaxAjoute;
    }
    
    public double getArmureAjoute() {
        return armureAjoute;
    }

    public boolean isPermanant() {
        return permanant;
    }

    public boolean isConsomable() {
        return consomable;
    }
    
    public double getAjoute(String nom){
        
        double r = 0.0;
        for (Property p : this.listproperties) {
            if (p.getNom().equals(nom)) {
                r += p.getValeur();
            }
        }
        
        return r;
    }
    
    /**
     *fonction a appeler a la fin du tour lorque l'on a deja getter les effets
     * @return retourn true si l'effet doit rester actif et false si l'effet est fini est doit etre detruit
     */
    public boolean tourPasse(){
        if(permanant){//effet permanant (l'effet restera actif pour toujours)
            tpSalle = null;
            return true;
        }
        if(consomable){//effet instantané (l'effet se detruira)
            return false;
        }
        if(duréeDeLEffet>=0){//effet a durée limité dans le temps(premiere fois que on utilise cette effet)
            tpSalle = null;
        }
        if(duréeDeLEffet == 1 ){//fin de l'effet a durée limité
            forceAjoute = 0;
            agiliteAjoute = 0;
            pvAjoute = 0;
            pvMaxAjoute = 0;
            tpSalle = null;
            poidsAjoute = 0;
            armureAjoute = 0;
            duréeDeLEffet = 0;
            return false;
        }
        
        if(this.duréeDeLEffet>=0){
            this.duréeDeLEffet-=1;
        }
        return true;
    }
    
    @Override
    public String toString(){
        String listesEffets = getNom()+": \n";
        if(getDuréeDeLEffet()>0){
            listesEffets += "Durée "+(int)getDuréeDeLEffet() + " Tours: \n"; 
        }else{
            listesEffets += "Effet passif: \n"; 
        }
        if(getPvAjoute()!=0){
            if(getPvAjoute()>0){
                listesEffets += "PV : +"+getPvAjoute()+" \n";
            }else{
                listesEffets += "PV : "+getPvAjoute()+" \n";
            }
        }
        if(getPvMaxAjoute()!=0){
            if(getPvMaxAjoute()>0){
                listesEffets += "PV max : +"+getPvMaxAjoute()+" \n";
            }else{
                listesEffets += "PV max : "+getPvMaxAjoute()+" \n";
            }
        }
        if(getForceAjoute()!=0){
            if(getForceAjoute()>0){
                listesEffets += "Force : +"+getForceAjoute()+" \n";
            }else{
                listesEffets += "Force : "+getForceAjoute()+" \n";
            }
        }
        if(getAgiliteAjoute()!=0){
            if(getAgiliteAjoute()>0){
                listesEffets += "Agilité : +"+getAgiliteAjoute()+" \n";
            }else{
                listesEffets += "Agilité : "+getAgiliteAjoute()+" \n";
            }
        }
        if(getPoidsAjoute()!=0){
            if(getPoidsAjoute()>0){
                listesEffets += "Poids : +"+getPoidsAjoute()+" \n";
            }else{
                listesEffets += "Poids : "+getPoidsAjoute()+" \n";
            }
        }
        if(getArmureAjoute()!=0){
            if(getArmureAjoute()>0){
                listesEffets += "Armure : +"+getArmureAjoute()+" \n";
            }else{
                listesEffets += "Armure : "+getArmureAjoute()+" \n";
            }
        }
        return listesEffets;
    }

}
