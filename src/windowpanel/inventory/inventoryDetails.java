/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package windowpanel.inventory;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author User
 */
public class inventoryDetails extends javax.swing.JPanel {

    /**
     * Creates new form inventoryDetails
     */
    public inventoryDetails() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        int w = this.getWidth();
        int h = this.getHeight();
        int s = 30;
        
        Graphics2D g2d = (Graphics2D)g;
        
        g2d.setColor(new Color(255,255,255,220));
        g2d.fillPolygon(new int[]{0,w-s,w,w,s,0,0}, new int[]{0,0,s,h,h,h-s,0}, 7);
        
        g2d.setColor(new Color(113,113,113));
        g2d.setStroke(new BasicStroke(10));
        g2d.drawPolygon(new int[]{0,w-s,w,w,s,0,0}, new int[]{0,0,s,h,h,h-s,0}, 7);
        
    }

}
