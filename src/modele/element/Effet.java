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
    private boolean permanant;
    private boolean consomable;
    private double duréeDeLEffet;
    private ArrayList<Property> listproperties; 

    public Effet(String Nom,String description , boolean permanant, boolean consomable, double duréeDeLEffet) {
        this.Nom = Nom;
        this.description = description;
        this.permanant = permanant;
        this.consomable = consomable;
        this.duréeDeLEffet = duréeDeLEffet;
        this.listproperties = new ArrayList<>();
    }
    
    public String getNom() {
        return Nom;
    }

    public boolean isPermanant() {
        return permanant;
    }

    public boolean isConsomable() {
        return consomable;
    }

    public double getDuréeDeLEffet() {
        return duréeDeLEffet;
    }
    
    public void setProperty(String nom, double valeur){
        for (Property p : this.listproperties) {
            if(p.getNom().equals(nom)){
                p.setValeur(valeur);
                return;
            }
        }
        this.listproperties.add(new Property(nom,valeur));
    }
    
    public void removeProperty(String nom){
        for (Property p : this.listproperties) {
            if(p.getNom().equals(nom)){
                listproperties.remove(p);
                return;
            }
        }
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
            return true;
        }
        if(consomable){//effet instantané (l'effet se detruira)
            return false;
        }
        if(duréeDeLEffet>=0){//effet a durée limité dans le temps(premiere fois que on utilise cette effet)
        }
        if(duréeDeLEffet == 1 ){//fin de l'effet a durée limité
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
        if(permanant){
            listesEffets += "Effet passif: \n"; 
        }
        if(duréeDeLEffet>0){
            listesEffets += "Durée "+(int)getDuréeDeLEffet() + " Tours: \n"; 
        }
        for (Property p : this.listproperties) {
            listesEffets += p.getNom()+ " " + p.getValeur();
        }
        return listesEffets;
    }
    
    
    public Effet copie() {
        Effet c = new Effet(Nom,description , permanant, consomable, duréeDeLEffet);
        
        for (Property p : this.listproperties) {
            c.setProperty(p.getNom(), p.getValeur());
        }
        
        return c;
    }

}
