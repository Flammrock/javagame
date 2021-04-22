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
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Light implements Drawable {
    
    int x;
    int y;
    int radius;
    
    public Light(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void draw(Canvas c, Graphics g) {
        //Graphics2D g2d = (Graphics2D) g;
        Graphics2D g2d = (Graphics2D) g;
        //g2d.setComposite(AlphaComposite.DstIn);
        RadialGradientPaint rgp = new RadialGradientPaint(
                (float)(c.toWorldX(x)), (float)(c.toWorldY(y)),
                (float)c.toScale(radius/2),
                new float[]{0f, 1f},
                new Color[]{new Color(255, 255, 255, 255),new Color(255, 255, 255, 0)}
        );
        g2d.setPaint(rgp);
        g2d.fill(new Arc2D.Float(c.toWorldX(x-radius/2), c.toWorldY(y-radius/2), c.toScale(radius), c.toScale(radius), 0, 360, Arc2D.PIE));
        /*RadialGradientPaint rgp = new RadialGradientPaint(
                (float)(c.toWorldX(x)), (float)(c.toWorldY(y)),
                (float)(radius/2),
                new float[]{0f, 1f},
                new Color[]{new Color(255, 255, 255, 255),new Color(255, 255, 255, 255)}
        );
        g2d.setPaint(rgp);
        g2d.fill(new Arc2D.Float(c.toWorldX(x-radius/2), c.toWorldY(y-radius/2), c.toScale(radius), c.toScale(radius), 0, 360, Arc2D.PIE));*/
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
