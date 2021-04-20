/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvas.collision;

import eventsystem.SimpleEvent;

/**
 *
 * @author User
 */
public class CollisionEvent extends SimpleEvent {
    
    Collisionable collider1;
    Collisionable collider2;
    
    public Collisionable getCollider1() {
        return this.collider1;
    }
    
    public Collisionable getCollider2() {
        return this.collider2;
    }
    
    public CollisionEvent(Collisionable data) {
        super((Object)data);
    }
    
    public CollisionEvent(Collisionable c1, Collisionable c2) {
        super(null);
        this.collider1 = c1;
        this.collider2 = c2;
    }
    
    
}
