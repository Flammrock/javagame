/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvas.collision;

import canvas.Drawable;

/**
 *
 * @author User
 */
public interface Collisionable extends Drawable {
    
    /**
     * Un Collisionable possède une CollisionBox et doit être capable d'en retourner une
     * @return retourne la collision box associé à l'objet (peut retourner null)
     */
    CollisionBox getCollisionBox();
    
     /**
     * Un Collisionable possède une CollisionBox et doit être capable de la définir
     * @param b La nouvelle collision box
     */
    void setCollisionBox(CollisionBox b);
    
    /**
     * Pour le Teste de collisions, la nouvelle coordonnée de l'objet doit être accepter par le testeur de collisions
     * (qui est géré par la classe Canvas)
     * Par conséquent, les méthodes :
     *        - moveTo(int x, int y);
     *        - moveBy(int x, int y);
     * sauf (setX() et setY())
     * où tout autre méthode permettant de déplacer l'objet doit tenir compte de ça
     * et mettre les nouvelles coordonnées qui doivent être retourner par getNewX() et getNewY()
     * 
     * @return retourne la nouvelle coordonnée en X
     */
    public int getNewX();
    
    /**
     * Pour le Teste de collisions, la nouvelle coordonnée de l'objet doit être accepter par le testeur de collisions
     * (qui est géré par la classe Canvas)
     * Par conséquent, les méthodes :
     *        - moveTo(int x, int y);
     *        - moveBy(int x, int y);
     * où tout autre méthode permettant de déplacer l'objet doit tenir compte de ça
     * et mettre les nouvelles coordonnées qui doivent être retourner par getNewX() et getNewY()
     * 
     * @return retourne la nouvelle coordonnée en Y
     */
    public int getNewY();
    
    /**
     * Pour le Teste de collisions, si les nouvelles coordonnées retournées
     * par getNewX() et getNewY() sont acceptées par le testeur de collisions,
     * alors cette fonction est appelé pour appliquer les nouvelles coordonnées (getNewX() et getNewY())
     */
    public void applyMove();
    
    /**
     * Pour le Teste de collisions, si les nouvelles coordonnées retournées
     * par getNewX() et getNewY() ne sont pas acceptées par le testeur de collisions,
     * alors cette fonction est appelé pour annuler les nouvelles coordonnées (getNewX() et getNewY())
     * (i.e. getNewX() et getNewY() doivent maintenant retourner l'ancienne position de l'objet)
     */
    public void cancelMove();
    
}
