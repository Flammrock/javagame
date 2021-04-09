/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.element;

import java.util.ArrayList;

/**
 *
 * @author Utilisateur
 */
public class Effet extends Element {
    private String Nom;
    private ArrayList<Double> duréeDeLEffet = new ArrayList<Double>();
    private ArrayList<Double> forceAjoute = new ArrayList<Double>();   //tableau ou l'indice: 0=effetCourant, 1=effetInitial, 2=effetContinu
    private ArrayList<Double> agiliteAjoute = new ArrayList<Double>();
    private ArrayList<Double> pvAjoute = new ArrayList<Double>();
    private ArrayList<Double> pvMaxAjoute = new ArrayList<Double>();
    private Lieu tpSalle;
    private ArrayList<Double> poidsAjoute = new ArrayList<Double>();
    private ArrayList<Double> armureAjoute = new ArrayList<Double>();

    public Effet(String Nom,String description, double duréeDeLEffet, double forceAjoute, double agiliteAjoute, double pvAjoute, Lieu tpSalle, double poidsAjoute) {
        this.Nom = Nom;
        this.description = description;
        this.duréeDeLEffet.add(0,duréeDeLEffet);
        this.forceAjoute.add(0,forceAjoute);
        this.agiliteAjoute.add(0,agiliteAjoute);
        this.pvAjoute.add(0,pvAjoute);
        this.pvMaxAjoute.add(0,(double)0);
        this.tpSalle = tpSalle;
        this.poidsAjoute.add(0,poidsAjoute);
        this.armureAjoute.add(0,(double)0);
    }

    

    public String getNom() {
        return Nom;
    }

    public double getDuréeDeLEffet() {
        return duréeDeLEffet.get(0);
    }

    public double getForceAjoute() {
        return forceAjoute.get(0);
    }

    public double getAgiliteAjoute() {
        return agiliteAjoute.get(0);
    }

    public double getPvAjoute() {
        return pvAjoute.get(0);
    }

    public Lieu getTpSalle() {
        return tpSalle;
    }

    public void setTpSalle(Lieu tpSalle) {
        this.tpSalle = tpSalle;
    }

    public double getPoidsAjoute() {
        return poidsAjoute.get(0);
    }
    
    public double getPvMaxAjoute() {
        return pvMaxAjoute.get(0);
    }
    
    public double getArmureAjoute() {
        return armureAjoute.get(0);
    }
    
    /**
     *fonction a appeler a la fin du tour lorque l'on a deja getter les effets
     * @return retourn true si l'effet doit rester actif et false si l'effet est fini est doit etre detruit
     */
    public boolean tourPasse(){
        if(duréeDeLEffet.get(0)==-1){//effet permanant (l'effet restera actif pour toujours)
            tpSalle = null;
            return true;
        }
        if(duréeDeLEffet.get(0)<-1){//effet instantané (l'effet se detruira)
            return false;
        }
        if(duréeDeLEffet.get(0)>=0){//effet a durée limité dans le temps(premiere fois que on utilise cette effet)
            tpSalle = null;
        }
        if(duréeDeLEffet.get(0) == 1 ){//fin de l'effet a durée limité
            forceAjoute.add(0,(double)0);
            agiliteAjoute.add(0,(double)0);
            pvAjoute.add(0,(double)0);
            pvMaxAjoute.add(0,(double)0);
            tpSalle = null;
            poidsAjoute.add(0,(double)0);
            armureAjoute.add(0,(double)0);
            duréeDeLEffet.add(0,(double)0);
            return false;
        }
        
        if(this.duréeDeLEffet.get(0)>=0){
            this.duréeDeLEffet.add(0,this.duréeDeLEffet.get(0)-1);
        }
        return true;
    }
    
    @Override
    public String toString(){
        String listesEffets = getNom()+" \n";
        if(duréeDeLEffet.get(0)>=0){
            if(getDuréeDeLEffet()>0){
                listesEffets += "Durée : "+(int)getDuréeDeLEffet() + " Tours \n"; 
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
        }
        return listesEffets;
    }
    
}
