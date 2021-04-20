/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import canvas.Canvas;
import canvas.Drawable;

/**
 *
 * @author User
 */
public class Camera {
    
    int wx;
    int wy;
    int sx;
    int sy;
    Drawable target;
    double scale;
    
    public Camera() {
        wx = 0;
        wy = 0;
        sx = 0;
        sy = 0;
        scale = 1;
        target = null;
    }
    
    public Camera(Drawable target) {
        this();
        this.target = target;
    }

    public int getWx() {
        return wx;
    }

    public void setWx(int wx) {
        this.wx = wx;
    }

    public int getWy() {
        return wy;
    }

    public void setWy(int wy) {
        this.wy = wy;
    }

    public int getSx() {
        return sx;
    }

    public void setSx(int sx) {
        this.sx = sx;
    }

    public int getSy() {
        return sy;
    }

    public void setSy(int sy) {
        this.sy = sy;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }
    
    
    
    
    

    public Drawable getTarget() {
        return target;
    }

    public void setTarget(Drawable target) {
        this.target = target;
    }

    public void zoomIn(double d) {
        this.scale = Math.min(5, this.scale*d);
    }

    public void zoomOut(double d) {
        this.scale = Math.max(0.001, this.scale/d);
    }

    public void update(Canvas canvas) {
        if (target == null) return;
        this.sx = canvas.getWidth()/2;
        this.sy = canvas.getHeight()/2;
        this.wx = (target!=null?target.getX():0);
        this.wy = (target!=null?target.getY():0);
    }
    
    
    
}
