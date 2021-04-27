/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.element;

import canvas.collision.Collisionable;
import eventsystem.SimpleListener;
import java.util.HashMap;

/**
 *
 * @author User
 */
public interface Ramassable extends Collisionable {
    
    /**
     * Un Ramassable doit pouvoir retourner un rayon minimal pour pouvoir ramasser l'objet
     * @return retourne le rayon permettant de récupérer l'objet
     */
    public int getRadiusRamassable();
    
    /**
     * Un Ramassable doit pouvoir se voir modifier son rayon de ramassabilité
     * @param radius le nouveau rayon
     */
    public void setRadiusRamassable(int radius);
    
    /**
     * Un Ramassable n'est probablement pas Ramassable à tout instant t.
     * Si cette méthode renvoie true, alors l'objet est ramassé directement
     * sinon l'objet n'est pas ramassé.
     * Autrement dit, c'est au Ramassable de gérer son rayon de ramassabilité en interne.
     * @param p le personnage qui ramassera le Ramassable
     * @return 
     */
    public boolean isRamassable(Personnage p);
    
    /**
     * Un Ramassable doit être capable de renvoyer la liste des tocuhes associés qui
     * permettent de ramasser le Ramassable.
     * Pour ramasser le Ramassable dès que this.isRamassable() renvoie true, cette méthode doit renvoyer null.
     * Par défaut, il faut appuyer sur "E" pour ramasser le Ramassable
     * @return retourne la HashMap qui contient la liste des keyCodes
     */
    default public HashMap<Integer,Boolean> getListKey() {
        HashMap ks = new HashMap();
        ks.put(69, true);
        return ks;
    }
    
    /**
     * Un Ramassable doit pouvoir être associer à une ou plusieurs touche.
     * @param keycode la touche à rajouter
     */
    default public void addKey(int keycode) {}
    
    /**
     * Un Ramassable doit pouvoir être désassocier de une ou plusieurs touche.
     * @param keycode la touche à enlever
     */
    default public void removeKey(int keycode) {}
    
    /**
     * Un ramassable doit pouvoir ajouter des écouteurs qui iront propager des events
     * dès qu'un Ramassable est ramassé.
     * @param l l'écouteur
     */
    default public void onRamasse(SimpleListener l) {
        if (this.getDispatcher()==null) return;
        l.setType("onRamasse");
        this.getDispatcher().addListener(l);
    }
    
}
