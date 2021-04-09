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
    
    ArrayList<Generable> generables;
    
    public GenerableLieuParametre() {
        this.generables = new ArrayList<>();
    }

    public ArrayList<Generable> getGenerables() {
        return generables;
    }

    public void setGenerables(ArrayList<Generable> generables) {
        this.generables = generables;
    }
    
    
    
    
    
    
}
