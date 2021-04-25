/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import eventsystem.Dispatcher;


/**
 *
 * @author User
 */
public interface Generable {
    
    /**
     * Un Generable possède un Dispatcher (lui permettant de propager des
     * Events comme des Events de Génération par exemple)
     * Par conséquent, un Générable doit pouvoir renvoyer un Dispatcher
     * (il peut renvoyer null si aucun Dispatcher n'est connecté)
     * @return retourne le Dispatcher
     */
    Dispatcher getDispatcher();
    
    /**
     * Permet d'attacher un écouteur qui est appelé dès que le Generable est sur le point d'être généré
     * @param l l'écouteur
     */
    public void onGenerate(GenerateListener l);
    
    /**
     * Permet de générer le Generable
     * @param o Object pouvant servir pour la génération
     * @return retourne true si la génération c'est bien passé, false sinon
     */
    public boolean generate(Object o);
    
}