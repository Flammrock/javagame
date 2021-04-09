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
    
    ArrayList<Personnage> monstres;
    ArrayList<Objet> objets;
    
    public GenerableLieuParametre() {
        this.monstres = new ArrayList<>();
        this.objets = new ArrayList<>();
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
