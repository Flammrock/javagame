/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvas;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 *
 * @author User
 */
public class TileSet extends Sprite {
    
    HashMap<String, Sprite> sprites;
    
    /**
     * Permet de créer un tileset depuis un fichier
     * @param spritefile le nom du fichier
     */
    public TileSet(String spritefile) {
        super(spritefile);
        this.sprites = new HashMap<>();
        
    }
    
    @Override
    public boolean loadImage() {
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream(this.spritefile));
            this.width = this.image.getWidth();
            this.height = this.image.getHeight();
            return true;
        } catch (IOException e) {}
        return false;
    }
    
    public void setSprite(String name, int sx, int sy, int swidth, int sheight) {
        if (!this.isLoaded()) return;
        Sprite s = new Sprite(this.spritefile);
        s.setSource(sx,sy,swidth,sheight);
        s.setImage(this.image);
        sprites.put(name, s);
    }
    
    public Sprite getSprite(String name) {
        if (!sprites.containsKey(name)) return null;
        if (sprites.get(name)==null) return null;
        Sprite s = sprites.get(name);
        Sprite d = new Sprite(s.getFileName());
        d.setSource(s.getSourceX(), s.getSourceY(), s.getSourceWidth(), s.getSourceHeight());
        return d;
    }
    
    
    @Override
    public void draw(Canvas c, Graphics g) {
        if (!this.isLoaded()) return;
        
        //if (this.ondraw!=null) this.ondraw.accept(c);
    }
    
}
