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
    
    public GenerableNiveauParametre(int nombreSalles) {
        this.nombreSalles = nombreSalles;
    }

    public int getNombreSalles() {
        return nombreSalles;
    }
    
    
    
}