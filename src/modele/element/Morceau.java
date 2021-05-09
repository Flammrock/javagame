/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.element;

import canvas.Drawable;
import canvas.Sprite;
import canvas.collision.CollisionBox;
import exec.Settings;

/**
 *
 * @author Utilisateur
 */
public class Morceau extends Objet {
    private int level;

    public Morceau(int level) {
        switch(level){
            case(Rarity.COMMON):
                this.nom = "Morceau commun";
                Sprite sprite_morceau;
                this.description = "Utile pour réparer et construre";
                sprite_morceau = new Sprite("/items/Item__39.png",0,0,24,24);
                sprite_morceau.addCollisionBox(new CollisionBox(0, 0, 24, 24,Settings.DEBUG,false));
                setSprite(sprite_morceau);
                setIcon(sprite_morceau.getFileName());
            break;
            case(Rarity.RARE):
                this.nom = "Morceau rare";
                this.description = "Utile pour la construction avancée";
                sprite_morceau = new Sprite("/items/Item__39.png",0,0,24,24);
                sprite_morceau.addCollisionBox(new CollisionBox(0, 0, 24, 24,Settings.DEBUG,false));
                setSprite(sprite_morceau);
                setIcon(sprite_morceau.getFileName());
            break;
            case(Rarity.EPIC):
                this.nom = "Morceau epic";
                this.description = "Utile pour la construction avancée";
                sprite_morceau = new Sprite("/items/Item__39.png",0,0,24,24);
                sprite_morceau.addCollisionBox(new CollisionBox(0, 0, 24, 24,Settings.DEBUG,false));
                setSprite(sprite_morceau);
                setIcon(sprite_morceau.getFileName());
            break;
            case(Rarity.LEGENDARY):
                this.nom = "Morceau legendaire";
                this.description = "Utile pour la construction avancée";
                sprite_morceau = new Sprite("/items/Item__39.png",0,0,24,24);
                sprite_morceau.addCollisionBox(new CollisionBox(0, 0, 24, 24,Settings.DEBUG,false));
                setSprite(sprite_morceau);
                setIcon(sprite_morceau.getFileName());
            break;
        }
        this.level = level;
        this.poids = 1;
    }

    public int getLevel() {
        return this.level;
    }

    @Override
    public Drawable copie() {
        Morceau c = new Morceau(this.level);
        c.setSprite(sprite);
        c.setIcon(this.icon);
        if (this.effet!=null) c.setEffet(this.effet.copie());
        c.setRadiusRamassable(this.radiusramassable);
        c.setZIndex(this.zindex);
        return c;
    }
}
