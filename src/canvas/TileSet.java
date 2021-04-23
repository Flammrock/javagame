/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvas;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
        boolean r = super.loadImage();
        if (!r) return false;
        BufferedImage image = this.getImage();
        this.width = image.getWidth();
        this.height = image.getHeight();
        return true;
    }
    
    public void setSprite(String name, int sx, int sy, int swidth, int sheight) {
        if (!this.isLoaded()) return;
        Sprite s = new Sprite(this.spritefile);
        s.setSource(sx,sy,swidth,sheight);
        s.setImage(this.spritefile);
        sprites.put(name, s);
    }
    
    public Sprite getSprite(String name) {
        if (!sprites.containsKey(name)) return null;
        if (sprites.get(name)==null) return null;
        Sprite s = sprites.get(name);
        Sprite d = new Sprite(s.getFileName());
        d.setImage(s.getFileName());
        d.setSource(s.getSourceX(), s.getSourceY(), s.getSourceWidth(), s.getSourceHeight());
        return d;
    }
    
    
    @Override
    public void draw(Canvas c, Graphics g) {
        if (!this.isLoaded()) return;
        
        //if (this.ondraw!=null) this.ondraw.accept(c);
    }
    
    
    public TileSet copie() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (TileSet) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
    
}
