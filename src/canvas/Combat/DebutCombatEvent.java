/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvas.Combat;

import eventsystem.SimpleEvent;
import modele.element.Personnage;

/**
 *
 * @author ssj-m_000
 */
public class DebutCombatEvent  extends SimpleEvent {
    Personnage Perso1;
    Personnage Perso2;

    public Personnage getPerso1() {
        return Perso1;
    }

    public Personnage getPerso2() {
        return Perso2;
    }
    
    public DebutCombatEvent(Personnage Perso1,Personnage Perso2) {
        super(0);
        this.Perso1 = Perso1;
        this.Perso2 = Perso2;
    }

    public DebutCombatEvent() {
        super(0);
    }
    
    
    
}
