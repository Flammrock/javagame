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
public class Morceau {
    private Rarity level;

    public Morceau(Rarity level) {
        this.level = level;
    }

    public Rarity getLevel() {
        return level;
    }

}
