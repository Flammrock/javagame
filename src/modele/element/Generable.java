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
public interface Generable {
    
    /**
     * Permet de générer le Generable (avec des valeurs prédéfini, des algos, du random...)
     * @param p Des paramètres pour la génératiob
     * @return
     */
    boolean generate(GenerableParametre p);
    
    /**
     * Permet de récupérer la probabilité d'un Generable
     * @return retourne la probabilité de générer le Generable
     */
    double getProbabilite();
    
    /**
     * Permet de définir la probabilité d'un Generable
     * @param proba la probabilité d'apparition
     */
    void setProbabilite(double proba);
    
}
