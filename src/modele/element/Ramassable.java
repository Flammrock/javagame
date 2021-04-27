/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.element;

import canvas.collision.Collisionable;
import eventsystem.SimpleListener;

/**
 *
 * @author User
 */
public interface Ramassable extends Collisionable {
    
    default public void onRamasse(SimpleListener l) {
        if (this.getDispatcher()==null) return;
        l.setType("onRamasse");
        this.getDispatcher().addListener(l);
    }
    
}
