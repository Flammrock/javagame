/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class Canvas extends JPanel {
    
    ArrayList<Drawable> itemsdrawable;
    Graphics crayon;
    
    public Canvas() {
        super();
        this.itemsdrawable = new ArrayList<Drawable>();
    }
    
    public void ajouterItem(Drawable item) {
        this.itemsdrawable.add(item);
    }
    
    public void mettreAJour() {
        if (this.crayon==null) return;
        this.paint(this.crayon);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        crayon = g;
        
        //Graphics2D graphic2d = (Graphics2D) g;
        //graphic2d.setColor(Color.BLUE);
        //graphic2d.fillRect(230, 110, 60, 80);
        
        //Sprite s = new Sprite("/spritetest.png",-100,-100);
        //s.load();
        //s.draw(this, g);
        
        for (Drawable item : this.itemsdrawable) {
            item.draw(this,g);
        }
        
    }

}
