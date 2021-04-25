/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvas.light;

import canvas.Canvas;
import canvas.Drawable;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Light implements Drawable {
    
    static public BufferedImage image = null;
    
    int x;
    int y;
    int radius;
    double v;
    
    Drawable parent;
    
    int tick;
    
    public Light(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.tick = 0;
        this.v = 0;
        this.parent = null;
    }

    @Override
    public void draw(Canvas c, Graphics g) {
        
        if (Light.image == null) {
            int s = 40;
            Light.image = new BufferedImage(s, s, BufferedImage.TYPE_INT_ARGB);
            Graphics g3 = Light.image.getGraphics();
            Graphics2D g3d = (Graphics2D)g3;
            g3d.setColor(new Color(0,0,0,0));
            g3d.fillRect(0, 0, s, s);
            g3d.setColor(Color.WHITE);
            g3d.fillArc(0, 0, s, s, 0, 360);
            g3d.dispose();
        }
        
        Graphics2D g2d = (Graphics2D)g;
        
        int r = radius - (int)(5*Math.sin(this.v));
        
        //g2d.setColor(Color.WHITE);
        //g2d.fillArc(c.toWorldX(x - r / 2), c.toWorldY(y - r / 2), c.toScale(r), c.toScale(r), 0, 360);
        
        g2d.drawImage(Light.image, c.toWorldX(x - r / 2), c.toWorldY(y - r / 2), c.toScale(r), c.toScale(r), null);

        if (this.tick > 10) {
            this.tick = 0;
            this.v += Math.PI/8;
        }
        
        this.tick++;
        
    }
    
    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void moveBy(int x, int y) {
        this.x += x;
        this.y += y;
    }
    
    @Override
    public Drawable copie() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (Light) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
    
    @Override
    public void setParent(Drawable d) {
        this.parent = d;
    }
    
    @Override
    public Drawable getParent() {
        return this.parent;
    }
    
}
