/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class Canvas extends JPanel {
    
    @Override
    public void paint(Graphics g) {
        
        Graphics2D graphic2d = (Graphics2D) g;
        graphic2d.setColor(Color.BLUE);
        graphic2d.fillRect(230, 110, 60, 80);
        
        Sprite s = new Sprite("/spritetest.png",-100,-100);
        s.load();
        s.draw(this, g);
        
    }

}
