/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvas.collision;

/**
 *
 * @author User
 */
public interface Collisionable {
    
    CollisionBox getCollisionBox();
    
    void setCollisionBox(CollisionBox b);
    
}
