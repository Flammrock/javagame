/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.element;

/**
 *
 * @author User
 */
public class GenerableNiveauParametre extends GenerableParametre {
    
    int nombreSalles;
    GenerableLieuParametre lieup;
    
    public GenerableNiveauParametre(int nombreSalles, GenerableLieuParametre lieup) {
        this.nombreSalles = nombreSalles;
        this.lieup = lieup;
    }

    public int getNombreSalles() {
        return nombreSalles;
    }
    
    public GenerableLieuParametre getGenerableLieuParametre() {
        return this.lieup;
    }
    
    
    
}
