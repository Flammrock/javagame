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
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Light implements Drawable {
    
    int x;
    int y;
    int radius;
    double v;
    
    int tick;
    
    BufferedImage image;
    
    public Light(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.image = null;
        this.tick = 0;
        this.v = 0;
    }

    @Override
    public void draw(Canvas c, Graphics g) {
        // pré-render image into buffer
        /*if (this.image == null) {
            this.image = new BufferedImage(radius, radius, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = this.image.createGraphics();
            RadialGradientPaint rgp = new RadialGradientPaint(
                (float)(radius/2), (float)(radius/2),
                (float)(radius/2),
                new float[]{0.5f, 1f},
                new Color[]{new Color(255, 255, 255, 100),new Color(255, 255, 255, 0)}
            );
            g2d.setPaint(rgp);
            g2d.fill(new Arc2D.Float(0, 0, radius, radius, 0, 360, Arc2D.PIE));
        }*/
        
        // draw light
        //g.drawImage(this.image, c.toWorldX(x-radius/2), c.toWorldY(y-radius/2), c.toScale(radius), c.toScale(radius), null);
        
        Graphics2D g2d = (Graphics2D)g;
        /*RadialGradientPaint rgp = new RadialGradientPaint(
                c.toWorldX(x), c.toWorldY(y),
                (float) (c.toScale(radius / 2)),
                new float[]{0f, 1f},
                new Color[]{new Color(255, 255, 255, 255), new Color(255, 255, 255, 0)}
        );
        g2d.setPaint(rgp);
        g2d.fill(new Arc2D.Float(c.toWorldX(x - radius / 2), c.toWorldY(y - radius / 2), c.toScale(radius), c.toScale(radius), 0, 360, Arc2D.PIE));*/
        
        int r = radius - (int)(5*Math.sin(this.v));
        
        g2d.setColor(Color.WHITE);
        g2d.fillArc(c.toWorldX(x - r / 2), c.toWorldY(y - r / 2), c.toScale(r), c.toScale(r), 0, 360);

        if (this.tick > 10) {
            this.tick = 0;
            this.v += Math.PI/8;
        }
        
        this.tick++;
        
    }

    @Override
    public ArrayList<Drawable> getDrawables() {
        return null;
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
    public boolean isDraw() {
        return true;
    }
    
}
