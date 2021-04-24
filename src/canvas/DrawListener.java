/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvas;

import eventsystem.SimpleEvent;
import eventsystem.SimpleListener;
import java.io.Serializable;

/**
 *
 * @author User
 */
public abstract class DrawListener extends SimpleListener implements Serializable {
    
    public DrawListener() {
        super("onDraw");
    }
    
    @Override
    public void onEvent(Object sender, SimpleEvent e){}
    
    public abstract void onDraw(Canvas c);
    
}