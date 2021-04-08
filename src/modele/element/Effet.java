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
    private double[] pvMaxAjoute = new double[3];
    private Lieu tpSalle;
    private double[] poidsAjoute = new double[3];
    private double[] armureAjoute = new double[3];

    public Effet(String Nom,String description, double duréeDeLEffet, double forceAjoute, double agiliteAjoute, double pvAjoute, Lieu tpSalle, double poidsAjoute) {
        this.Nom = Nom;
        this.description = description;
        this.duréeDeLEffet[0] = duréeDeLEffet;
        this.duréeDeLEffet[1] = duréeDeLEffet;
        this.forceAjoute[0] = forceAjoute;
        this.forceAjoute[1] = forceAjoute;
        this.forceAjoute[2] = 0;
        this.agiliteAjoute[0] = agiliteAjoute;
        this.agiliteAjoute[1] = agiliteAjoute;
        this.agiliteAjoute[2] = 0;
        this.pvAjoute[0] = pvAjoute;
        this.pvAjoute[1] = pvAjoute;
        this.pvAjoute[2] = 0;
        this.pvMaxAjoute[0] = 0;
        this.pvMaxAjoute[1] = 0;
        this.pvMaxAjoute[2] = 0;
        this.tpSalle = tpSalle;
        this.poidsAjoute[0] = poidsAjoute;
        this.poidsAjoute[1] = poidsAjoute;
        this.poidsAjoute[2] = 0;
        this.armureAjoute[0] = 0;   //a ajouter
        this.armureAjoute[1] = 0;
        this.armureAjoute[2] = 0;
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
    
    public double getPvMaxAjoute() {
        return pvMaxAjoute[0];
    }
    
    public double getArmureAjoute() {
        return armureAjoute[0];
    }
    
    /**
     *fonction a appeler a la fin du tour lorque l'on a deja getter les effets
     * @return retourn true si l'effet doit rester actif et false si l'effet est fini est doit etre detruit
     */
    public boolean tourPasse(){
        if(duréeDeLEffet[0]==duréeDeLEffet[1] && duréeDeLEffet[0]==-1){//effet permanant (l'effet restera actif pour toujours)
            tpSalle = null;
            return true;
        }
        if(duréeDeLEffet[0]==duréeDeLEffet[1] && duréeDeLEffet[0]<-1){//effet instantané (l'effet se detruira)
            forceAjoute[0] = 0;
            agiliteAjoute[0] = 0;
            pvAjoute[0] = 0;
            tpSalle = null;
            poidsAjoute[0] = 0;
            pvMaxAjoute[0] = 0;
            armureAjoute[0] = 0;
            return false;
        }
        if(duréeDeLEffet[0]==duréeDeLEffet[1] && duréeDeLEffet[0]>-1){//effet a durée limité dans le temps(premiere fois que on utilise cette effet)
            tpSalle = null;
        }
        if(duréeDeLEffet[0] == 0 ){//fin de l'effet a durée limité
            forceAjoute[0] = 0;
            agiliteAjoute[0] = 0;
            pvAjoute[0] = 0;
            pvMaxAjoute[0] = 0;
            tpSalle = null;
            poidsAjoute[0] = 0;
            armureAjoute[0] = 0;
            return false;
        }
        
        if(this.duréeDeLEffet[0]>=0){
            this.duréeDeLEffet[0]--;
        }
        return true;
    }
    
    @Override
    public String toString(){
        String listesEffets = getNom()+"\n";
        if(duréeDeLEffet[0]>=0){
            if(getDuréeDeLEffet()>0){
                listesEffets += "Durée : "+getDuréeDeLEffet() + "Tours\n"; 
            }
            if(getPvAjoute()!=0){
                if(getPvAjoute()>0){
                    listesEffets += "PV : +"+getPvAjoute()+"\n";
                }else{
                    listesEffets += "PV : "+getPvAjoute()+"\n";
                }
            }
            if(getPvMaxAjoute()!=0){
                if(getPvMaxAjoute()>0){
                    listesEffets += "PV max : +"+getPvMaxAjoute()+"\n";
                }else{
                    listesEffets += "PV max : "+getPvMaxAjoute()+"\n";
                }
            }
            if(getForceAjoute()!=0){
                if(getForceAjoute()>0){
                    listesEffets += "Force : +"+getForceAjoute()+"\n";
                }else{
                    listesEffets += "Force : "+getForceAjoute()+"\n";
                }
            }
            if(getAgiliteAjoute()!=0){
                if(getAgiliteAjoute()>0){
                    listesEffets += "Agilité : +"+getAgiliteAjoute()+"\n";
                }else{
                    listesEffets += "Agilité : "+getAgiliteAjoute()+"\n";
                }
            }
            if(getPoidsAjoute()!=0){
                if(getPoidsAjoute()>0){
                    listesEffets += "Poids : +"+getPoidsAjoute()+"\n";
                }else{
                    listesEffets += "Poids : "+getPoidsAjoute()+"\n";
                }
            }
            if(getArmureAjoute()!=0){
                if(getArmureAjoute()>0){
                    listesEffets += "Armure : +"+getArmureAjoute()+"\n";
                }else{
                    listesEffets += "Armure : "+getArmureAjoute()+"\n";
                }
            }
        }
        return listesEffets;
    }
    
}
