/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.element;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public class GenerableLieuParametre extends GenerableParametre {
    
    double proba_monstre;
    double proba_objet;
    
    ArrayList<Personnage> monstres;
    ArrayList<Objet> objets;
    
    public GenerableLieuParametre(double proba_monstre, double proba_objet) {
        this.proba_monstre = proba_monstre;
        this.proba_objet = proba_objet;
        this.monstres = new ArrayList<>();
        this.objets = new ArrayList<>();
    }

    public double getProba_monstre() {
        return proba_monstre;
    }

    public double getProba_objet() {
        return proba_objet;
    }

    public ArrayList<Personnage> getMonstres() {
        return monstres;
    }

    public void setMonstres(ArrayList<Personnage> monstres) {
        this.monstres = monstres;
    }

    public ArrayList<Objet> getObjets() {
        return objets;
    }

    public void setObjets(ArrayList<Objet> objets) {
        this.objets = objets;
    }
    
    
    
}
