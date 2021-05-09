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
    
    /**
     * Permet de savoir si deux Collisionable sont en collisions.Pour que ce soit plus pratique, cette méthode n'est pas à implémenter
 sauf dans des cas très spécifique.
     * @param other l'autre Collisionable
     * @param evenInactive true pour prendre en compte les CollisionBox inactive sinon false pour les ignorer
     * @return retourne null si la collision est invalide (pas de collisionBox, même objet en mémoire..),
     *         sinon renvoie true s'il y a collision sinon false
     */
    default Boolean isCollide(Collisionable other, boolean evenInactive) {
        
        // évidemment, on veut que les Collisionable soient bien deux objets distincts
        if (this == other) {
            return null;
        }

        // on récupère les ensembles de box
        ArrayList<CollisionBox> list1 = this.getCollisionBoxList();
        ArrayList<CollisionBox> list2 = other.getCollisionBoxList();

        if (list1 == null) {
            return null;
        }
        if (list2 == null) {
            return null;
        }

        // on les places au bon endroit pour le test
        for (CollisionBox b : list1) {
            b.apply(this.getNewX(), this.getNewY());
        }
        for (CollisionBox b : list2) {
            b.apply(other.getNewX(), other.getNewY());
        }

        // on check s'il y a une collision
        for (CollisionBox b1 : list1) {

            for (CollisionBox b2 : list2) {
                
                if (!evenInactive && (!b1.isActive() || !b2.isActive())) continue;

                if (b1.isCollide(b2)) {

                    // on envoie un event de collisions vers les deux objets
                    this.collide(other);
                    other.collide(this);

                    if (!(b1.isGhost() || b2.isGhost())) return true;

                }

            }

        }
        
        return false;
    }
    
    
    /**
     * Permet d'estimer la longueur d'un Collisionable
     * @return retourne la longueur estimée
     */
    default public int computeWidth() {
        ArrayList<CollisionBox> bs = this.getCollisionBoxList();
        if (bs==null) return 0;
        if (bs.isEmpty()) return 0;
        int xmin = bs.get(0).getX();
        int xmax = xmin;
        for (CollisionBox b : bs) {
            if (b.getX() < xmin) xmin = b.getX();
            if (b.getX() > xmax) xmax = b.getX();
        }
        return xmax-xmin;
    }
    
    /**
     * Permet d'estimer la hauteur d'un Collisionable
     * @return retourne la hauteur estimée
     */
    default public int computeHeight() {
        ArrayList<CollisionBox> bs = this.getCollisionBoxList();
        if (bs==null) return 0;
        if (bs.isEmpty()) return 0;
        int ymin = bs.get(0).getY();
        int ymax = ymin;
        for (CollisionBox b : bs) {
            if (b.getY() < ymin) ymin = b.getY();
            if (b.getY() > ymax) ymax = b.getY();
        }
        return ymax-ymin;
    }
    
}
