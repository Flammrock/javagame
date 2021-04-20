/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvas.collision;

import canvas.Drawable;
import eventsystem.Dispatcher;
import eventsystem.SimpleListener;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public interface Collisionable extends Drawable {
    
    /**
     * Un Collisionable possède une liste de CollisionBox et doit être capable d'en retourner une
     * @return retourne la liste collision box associé à l'objet (peut retourner null)
     */
    ArrayList<CollisionBox> getCollisionBoxList();
    
     /**
     * Un Collisionable possède une liste de CollisionBox et doit être capable de la définir
     * @param b La nouvelle box à ajouter
     */
    void addCollisionBox(CollisionBox b);
    
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
    
    /**
     * Un Collisionable possède un Dispatcher (lui permettant de propager des
     * Events comme des Events de Collision par exemple)
     * Par conséquent, un Collisionable doit pouvoir renvoyer un Dispatcher
     * (il peut renvoyer null si aucun Dispatcher n'est connecté)
     * @return retourne le Dispatcher
     */
    Dispatcher getDispatcher();
    
    /**
     * Un Collisionable doit posséder une méthode lui permettant de propager
     * un Event de Collision via son Dispatcher
     * (ceci est automatiquement gérer par la classe Canvas)
     * (Canvas appelera cette méthode si les deux Collisionable possèdent
     * des CollisionBox)
     * @param c l'autre collisionable
     */
    void collide(Collisionable c);
    
    /**
     * Un Collisionable doit pouvoir avoir une méthode permettant d'ajouter directement des écouteurs
     * pour les collisions
     * @param l l'écouteur qui écoutera les collisions
     */
    void onCollide(SimpleListener l);
}
