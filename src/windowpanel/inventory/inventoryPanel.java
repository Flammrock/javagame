/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package windowpanel.inventory;

import canvas.Canvas;
import java.awt.BorderLayout;
import exec.AppliGraphique;
import java.util.ArrayList;
import modele.element.Arme;
import modele.element.Armure;
import modele.element.Nourriture;
import modele.element.Objet;
import modele.element.Personnage;

/**
 *
 * @author User
 */
public class inventoryPanel extends javax.swing.JPanel {

    private AppliGraphique app;
    private Objet current;
    private Personnage temp_p;
    private int selected;

    /**
     * Creates new form inventoryPanel
     */
    public inventoryPanel(AppliGraphique app) {
        this.app = app;
        
        initComponents();
        
        this.current = new Arme("","",0);
        
        this.selected = -1;
        
        this.temp_p = null;
        
        this.inventoryCategory1.setImage("/items/Item__01.png");
        this.inventoryCategory4.setImage("/items/Item__45.png");
        this.inventoryCategory2.setImage("/items/Item__64.png");
        this.inventoryCategory3.setImage("/items/Item__38.png");
        
        this.inventoryCategory1.select();
        
        this.jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (temp_p == null) return;
                ArrayList<Objet> inv = temp_p.getInventaire();
                int row = jTable2.rowAtPoint(evt.getPoint());
                int col = jTable2.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0 && (row*4+col) < inv.size()) {
                    prepare(temp_p,row*4+col);
                }
            }
        });
    }
    
    
    public void prepare(Personnage p, int selected) {
        
        if (p == null) return;
        
        int selec = selected;
        this.temp_p = p;
        this.selected = -1;
        
        ArrayList<Objet> inv = p.getInventaire();
        
        int s = 0;
        for (Objet o : inv) {
            if (
                (this.current == null && !(o instanceof Arme) && !(o instanceof Armure) && !(o instanceof Nourriture)) ||
                (this.current != null && o.getClass().isAssignableFrom(this.current.getClass()))
            ) {
                s++;
            }
        }
        
        Object os[][] = new Object[(int)Math.ceil(s/4.0)][4];
        int i = 0;
        int j = 0;
        int k = 0;
        for (Objet o : inv) {
            if (
                (this.current == null && !(o instanceof Arme) && !(o instanceof Armure) && !(o instanceof Nourriture)) ||
                (this.current != null && o.getClass().isAssignableFrom(this.current.getClass()))
            ) {
                if (i==0 && j==0 || (selec >= 0 && (i*4+j)==selec)) {
                    inventoryDetails2.set(o,k);
                    this.selected = k;
                }
                os[i][j++] = o;
                if (j==4) {
                    i++;
                    j = 0;
                }
            }
            k++;
        }
        
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            os,
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        inventoryPanelList1 = new windowpanel.inventory.inventoryPanelList();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable(){

            @Override
            public int getRowHeight() {
                return this.getColumnModel().getColumn(0).getWidth();
            }

        };
        jPanel1 = new javax.swing.JPanel();
        inventoryCategory1 = new windowpanel.inventory.inventoryCategory();
        inventoryCategory4 = new windowpanel.inventory.inventoryCategory();
        inventoryCategory2 = new windowpanel.inventory.inventoryCategory();
        inventoryCategory3 = new windowpanel.inventory.inventoryCategory();
        inventoryDetails2 = new windowpanel.inventory.inventoryDetails(this.app);

        setBackground(new java.awt.Color(255, 0, 0));
        setOpaque(false);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                formMouseWheelMoved(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Sitka Display", 0, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Inventaire");

        jButton1.setBackground(new java.awt.Color(255, 51, 51));
        jButton1.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jButton1.setText("Fermer");
        jButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable2.setFocusable(false);
        jTable2.setOpaque(false);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable2);
        jTable2.setTableHeader(null);
        jTable2.setRowSelectionAllowed(false);
        jTable2.setCellSelectionEnabled(true);

        javax.swing.GroupLayout inventoryPanelList1Layout = new javax.swing.GroupLayout(inventoryPanelList1);
        inventoryPanelList1.setLayout(inventoryPanelList1Layout);
        inventoryPanelList1Layout.setHorizontalGroup(
            inventoryPanelList1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
            .addGroup(inventoryPanelList1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(inventoryPanelList1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inventoryPanelList1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        inventoryPanelList1Layout.setVerticalGroup(
            inventoryPanelList1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventoryPanelList1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton1.setFocusPainted(false);

        jPanel1.setOpaque(false);

        inventoryCategory1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inventoryCategory1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                inventoryCategory1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                inventoryCategory1MouseExited(evt);
            }
        });

        javax.swing.GroupLayout inventoryCategory1Layout = new javax.swing.GroupLayout(inventoryCategory1);
        inventoryCategory1.setLayout(inventoryCategory1Layout);
        inventoryCategory1Layout.setHorizontalGroup(
            inventoryCategory1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 63, Short.MAX_VALUE)
        );
        inventoryCategory1Layout.setVerticalGroup(
            inventoryCategory1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 53, Short.MAX_VALUE)
        );

        inventoryCategory4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inventoryCategory4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                inventoryCategory4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                inventoryCategory4MouseExited(evt);
            }
        });

        javax.swing.GroupLayout inventoryCategory4Layout = new javax.swing.GroupLayout(inventoryCategory4);
        inventoryCategory4.setLayout(inventoryCategory4Layout);
        inventoryCategory4Layout.setHorizontalGroup(
            inventoryCategory4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        inventoryCategory4Layout.setVerticalGroup(
            inventoryCategory4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 53, Short.MAX_VALUE)
        );

        inventoryCategory2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inventoryCategory2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                inventoryCategory2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                inventoryCategory2MouseExited(evt);
            }
        });

        javax.swing.GroupLayout inventoryCategory2Layout = new javax.swing.GroupLayout(inventoryCategory2);
        inventoryCategory2.setLayout(inventoryCategory2Layout);
        inventoryCategory2Layout.setHorizontalGroup(
            inventoryCategory2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        inventoryCategory2Layout.setVerticalGroup(
            inventoryCategory2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 53, Short.MAX_VALUE)
        );

        inventoryCategory3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inventoryCategory3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                inventoryCategory3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                inventoryCategory3MouseExited(evt);
            }
        });

        javax.swing.GroupLayout inventoryCategory3Layout = new javax.swing.GroupLayout(inventoryCategory3);
        inventoryCategory3.setLayout(inventoryCategory3Layout);
        inventoryCategory3Layout.setHorizontalGroup(
            inventoryCategory3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        inventoryCategory3Layout.setVerticalGroup(
            inventoryCategory3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 53, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(inventoryCategory1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(inventoryCategory4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(inventoryCategory2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(inventoryCategory3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addComponent(inventoryCategory1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inventoryCategory4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inventoryCategory2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inventoryCategory3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(150, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(inventoryPanelList1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inventoryDetails2, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(103, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(162, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(inventoryDetails2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inventoryPanelList1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(159, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        evt.consume();
    }//GEN-LAST:event_formKeyPressed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        evt.consume();
    }//GEN-LAST:event_formKeyReleased

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
        evt.consume();
    }//GEN-LAST:event_formKeyTyped

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        evt.consume();
    }//GEN-LAST:event_formMouseClicked

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        evt.consume();
    }//GEN-LAST:event_formMouseDragged

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        evt.consume();
    }//GEN-LAST:event_formMouseEntered

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        evt.consume();
    }//GEN-LAST:event_formMouseExited

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        evt.consume();
    }//GEN-LAST:event_formMouseMoved

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        evt.consume();
    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        evt.consume();
    }//GEN-LAST:event_formMouseReleased

    private void formMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_formMouseWheelMoved
        evt.consume();
    }//GEN-LAST:event_formMouseWheelMoved

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.app.hideInventory();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void inventoryCategory1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventoryCategory1MouseEntered
        this.inventoryCategory1.hover();
    }//GEN-LAST:event_inventoryCategory1MouseEntered

    private void inventoryCategory1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventoryCategory1MouseExited
        this.inventoryCategory1.unhover();
    }//GEN-LAST:event_inventoryCategory1MouseExited

    private void inventoryCategory4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventoryCategory4MouseEntered
        this.inventoryCategory4.hover();
    }//GEN-LAST:event_inventoryCategory4MouseEntered

    private void inventoryCategory4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventoryCategory4MouseExited
        this.inventoryCategory4.unhover();
    }//GEN-LAST:event_inventoryCategory4MouseExited

    private void inventoryCategory2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventoryCategory2MouseEntered
        this.inventoryCategory2.hover();
    }//GEN-LAST:event_inventoryCategory2MouseEntered

    private void inventoryCategory2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventoryCategory2MouseExited
        this.inventoryCategory2.unhover();
    }//GEN-LAST:event_inventoryCategory2MouseExited

    private void inventoryCategory3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventoryCategory3MouseEntered
        this.inventoryCategory3.hover();
    }//GEN-LAST:event_inventoryCategory3MouseEntered

    private void inventoryCategory3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventoryCategory3MouseExited
        this.inventoryCategory3.unhover();
    }//GEN-LAST:event_inventoryCategory3MouseExited

    private void inventoryCategory1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventoryCategory1MouseClicked
        if (this.inventoryCategory1.isSelected()) return;
        this.inventoryCategory1.select();
        this.inventoryCategory4.unselect();
        this.inventoryCategory3.unselect();
        this.inventoryCategory2.unselect();
        this.current = new Arme("","",0);
        this.prepare(this.temp_p,-1);
    }//GEN-LAST:event_inventoryCategory1MouseClicked

    private void inventoryCategory4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventoryCategory4MouseClicked
        if (this.inventoryCategory4.isSelected()) return;
        this.inventoryCategory1.unselect();
        this.inventoryCategory4.select();
        this.inventoryCategory3.unselect();
        this.inventoryCategory2.unselect();
        this.current = new Armure("","",0);
        this.prepare(this.temp_p,-1);
    }//GEN-LAST:event_inventoryCategory4MouseClicked

    private void inventoryCategory2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventoryCategory2MouseClicked
        if (this.inventoryCategory2.isSelected()) return;
        this.inventoryCategory1.unselect();
        this.inventoryCategory4.unselect();
        this.inventoryCategory3.unselect();
        this.inventoryCategory2.select();
        this.current = new Nourriture("","",0);
        this.prepare(this.temp_p,-1);
    }//GEN-LAST:event_inventoryCategory2MouseClicked

    private void inventoryCategory3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventoryCategory3MouseClicked
        if (this.inventoryCategory3.isSelected()) return;
        this.inventoryCategory1.unselect();
        this.inventoryCategory4.unselect();
        this.inventoryCategory3.select();
        this.inventoryCategory2.unselect();
        this.current = null;
        this.prepare(this.temp_p,-1);
    }//GEN-LAST:event_inventoryCategory3MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private windowpanel.inventory.inventoryCategory inventoryCategory1;
    private windowpanel.inventory.inventoryCategory inventoryCategory2;
    private windowpanel.inventory.inventoryCategory inventoryCategory3;
    private windowpanel.inventory.inventoryCategory inventoryCategory4;
    private windowpanel.inventory.inventoryDetails inventoryDetails2;
    private windowpanel.inventory.inventoryPanelList inventoryPanelList1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
