/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvas;

import canvas.light.Light;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public interface Drawable extends Serializable {
    
    /**
     * Propagation de l'Appel draw() (cet appel est d'origine de Canvas et est appelé à 60FPS)
     * @param c Le Canvas
     * @param g L'objet Graphics
     */
    void draw(Canvas c, Graphics g);
    
    /**
     * Un Drawable peut être un sous-ensemble de Drawable
     * Cette méthode permet de retourner ce sous-ensemble
     * (fonction appelé par Canvas pour récupérer la liste de tout les Drawable du jeu)
     * 
     * Néanmoins, ce "sous-ensemble" peut être entièrement gérer par l'objet lui-même,
     * pour cela, il suffit de renvoyer null ici et que l'objet dessine soi-même ce sous-ensemble
     * lui même à l'aide de la méthode draw(Canvas c, Graphics g)
     * 
     * @return retourne la liste du sous-ensemble
     */
    default public ArrayList<Drawable> getDrawables() {
        return null;
    }
    
    /**
     * Chaque Drawable est censé pouvoir être dessiner dans le canvas,
     * par conséquent, l'objet qui implémente cette interface doit pouvoir être capable de
     * retourner une coordonnée X qui définit sa position dans le canvas
     * @return retourne la position X de l'objet
     */
    public int getX();

    /**
     * Chaque Drawable est censé pouvoir être dessiner dans le canvas,
     * par conséquent, l'objet qui implémente cette interface doit pouvoir être capable de
     * définir sa coordonnée X qui définit sa position dans le canvas
     * @param x La nouvelle position X de l'objet
     */
    public void setX(int x);

    /**
     * Chaque Drawable est censé pouvoir être dessiner dans le canvas,
     * par conséquent, l'objet qui implémente cette interface doit pouvoir être capable de
     * retourner une coordonnée Y qui définit sa position dans le canvas
     * @return retourne la position Y de l'objet
     */
    public int getY();

    /**
     * Chaque Drawable est censé pouvoir être dessiner dans le canvas,
     * par conséquent, l'objet qui implémente cette interface doit pouvoir être capable de
     * définir sa coordonnée Y qui définit sa position dans le canvas
     * @param y La nouvelle position Y de l'objet
     */
    public void setY(int y);
    
    
    /**
     * Chaque Drawable est censé pouvoir être dessiner dans le canvas,
     * par conséquent, l'objet qui implémente cette interface doit pouvoir être capable de
     * définir sa coordonnée X et Y
     * @param x La nouvelle position X de l'objet
     * @param y La nouvelle position Y de l'objet
     */
    public void moveTo(int x, int y);
    
    /**
     * Chaque Drawable est censé pouvoir être dessiner dans le canvas,
     * par conséquent, l'objet qui implémente cette interface doit pouvoir être capable de
     * définir sa coordonnée X et Y de manière relative
     * @param x La nouvelle position X relative de l'objet
     * @param y La nouvelle position Y relative de l'objet
     */
    public void moveBy(int x, int y);
    
    
    /**
     * Permet de savoir si un Drawable doit être dessiner ou pas.
     * Si cette méthode renvoie true, alors ce Drawable sera dessiné, sinon il ne sera pas dessiné.
     * @return retourne true pour être dessiner, false sinon
     */
    default public boolean isDraw() {
        return true;
    }
    
    
}
