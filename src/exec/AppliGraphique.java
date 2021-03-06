/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exec;

import canvas.Canvas;
import canvas.Combat.CombatGraphique2;
import canvas.Combat.DebutCombatEvent;
import eventsystem.SimpleEvent;
import eventsystem.SimpleListener;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.OverlayLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import map.Camera;
import modele.element.*;
import windowpanel.creditPanel;
import windowpanel.gameoverPanel;
import windowpanel.inventory.inventoryPanel;
import windowpanel.titlePanel;
import windowpanel.uiPanel;


/**
 *
 * @author pierrecharbit
 */
public class AppliGraphique extends javax.swing.JFrame {
    
    private Aventure aventure;
    private inventoryPanel invpanel;
    private uiPanel uipanel;
    private gameoverPanel gameoverpanel;
    private titlePanel titlepanel;
    private creditPanel creditpanel;
    
    /**
     * Creates new form AppliGraphique
     * @param aventure
     */
    public AppliGraphique(Aventure aventure) {
        this.aventure = aventure;
        initComponents();
        
        initCustom();

        AppliGraphique _this = this;
        /*listeObjetInventaire.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    _this.onSelectObjetInventaire();
                }
            }
        });*/
        
        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        this.setTitle("ULTRA BASIC SIMPLE GAME EVEN A NOOB PROGRAMER CAN MAKE | V1.0.6654.1874115");
        
        
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new MyDispatcher());
        
        Camera cam = new Camera(this.aventure.getJoueur());
        canvas1.setCamera(cam);
        canvas1.setColor(new Color(28,17,23));
        
        combatGraphique22.bindAventure(this.aventure);
        combatGraphique22.setVisible(false);
        canvas1.ajouterItem(this.aventure);
        
        //canvas1.ajouterItem(new Light(0,0,200));
        
        new Timer("Drawer", true).scheduleAtFixedRate( new TimerTask(){
            public void run(){
               if (!_this.aventure.isPauseDraw()) canvas1.repaint();
               combatGraphique22.mettreAJourCanvas();
            }
        }, 
        100, // Start in 100 ms
        (int)(1000 / 60));
        

        mettreAJourTout();
        
        this.aventure.addListerner(new SimpleListener("onCombatCommence"){
            @Override
            public void onEvent(Object sender, SimpleEvent e) {
                Aventure aventure = (Aventure) sender;
                DebutCombatEvent p = (DebutCombatEvent)e;
                Personnage perso1 = p.getPerso1();
                Personnage perso2 = p.getPerso2();
                perso1.beginFight();
                perso2.beginFight();
                combatGraphique22.bindAventure(aventure);
                combatGraphique22.lancerCombat(perso1,perso2);
                combatGraphique22.setVisible(true);
                
            }
        });
    
        this.aventure.addListerner(new SimpleListener("onCombatTermine"){
            @Override
            public void onEvent(Object sender, SimpleEvent e) {
                combatGraphique22.setVisible(false);
                Aventure aventure = (Aventure) sender;
                DebutCombatEvent p = (DebutCombatEvent)e;
                Personnage perso1 = p.getPerso1();
                Personnage perso2 = p.getPerso2();
                perso1.endFight();
                perso2.endFight();
                if (perso2.getAjoute(PropertyList.PV) <= 0) {
                    aventure.getJoueur().getPieceActuel().kill(perso2);
                }
            }
        });
        
        this.aventure.addListerner(new SimpleListener("gameOver") {
            @Override
            public void onEvent(Object sender, SimpleEvent e) {
                if(sender instanceof Personnage){
                    Personnage p = (Personnage)sender;
                    if(p == aventure.getJoueur()){
                        showGameOver();
                    }
                }
            }
        });
        
        //Combattre.setVisible(false);
        //allerDansPorte.setVisible(false);
        //canvas1.setVisible(false);
        /*jPanel1.setVisible(false);
        jPanel2.setVisible(false);
        jPanel3.setVisible(false);
        jPanel4.setVisible(false);
        jPanel5.setVisible(false);
        jPanel6.setVisible(false);
        jScrollPane1.setVisible(false);
        jScrollPane10.setVisible(false);
        jScrollPane11.setVisible(false);
        jScrollPane12.setVisible(false);
        jScrollPane13.setVisible(false);
        jScrollPane14.setVisible(false);
        jScrollPane15.setVisible(false);
        jScrollPane16.setVisible(false);
        jScrollPane19.setVisible(false);
        jScrollPane2.setVisible(false);
        jScrollPane20.setVisible(false);
        jScrollPane21.setVisible(false);
        jScrollPane22.setVisible(false);
        jScrollPane23.setVisible(false);
        jScrollPane3.setVisible(false);
        jScrollPane4.setVisible(false);
        jScrollPane5.setVisible(false);
        jScrollPane6.setVisible(false);
        jScrollPane7.setVisible(false);
        jScrollPane8.setVisible(false);
        jScrollPane9.setVisible(false);
        jTextPane1.setVisible(false);
        jTextPane2.setVisible(false);
        jTextPaneAgilite.setVisible(false);
        jTextPaneAgiliteFixe.setVisible(false);
        jTextPaneArmeEnMainFixe.setVisible(false);
        jTextPaneArmureFixe.setVisible(false);
        jTextPaneForce.setVisible(false);
        jTextPaneForceFixe.setVisible(false);
        jTextPaneInventaire.setVisible(false);
        jTextPaneNom.setVisible(false);
        jTextPaneNomFixe.setVisible(false);
        jTextPanePv.setVisible(false);
        jTextPanePvFixe.setVisible(false);
        jeterBouton.setVisible(false);
        listeEffetJoueur.setVisible(false);
        listeMonstre.setVisible(false);
        listeObjet.setVisible(false);
        listeObjetInventaire.setVisible(false);
        listePortes.setVisible(false);
        logger.setVisible(false);
        ramasser.setVisible(false);
        textArmeEnMain.setVisible(false);
        textArmure.setVisible(false);
        utiliserBouton.setVisible(false);*/
    }
    
    public void initCustom() {
        
        
        canvas1 = new Canvas();
        combatGraphique22 = new CombatGraphique2();
        invpanel = new inventoryPanel(this);
        uipanel = new uiPanel(this);
        gameoverpanel = new gameoverPanel(this);
        titlepanel = new titlePanel(this);
        creditpanel = new creditPanel(this);
        
        layeredpane = new JLayeredPane();
        layeredpane.setBackground(Color.red);
        layeredpane.setLayout(new OverlayLayout(layeredpane));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        layeredpane.add(canvas1, JLayeredPane.DEFAULT_LAYER);
        layeredpane.add(combatGraphique22, JLayeredPane.MODAL_LAYER);
        //layeredpane.add(invpanel, JLayeredPane.POPUP_LAYER);
        layeredpane.add(uipanel, JLayeredPane.PALETTE_LAYER);
        
        layeredpane.add(titlepanel, Integer.valueOf(19000));
        
        setContentPane(layeredpane);
        
    }
    
    public void hideCreditScreen() {
        layeredpane.remove(creditpanel);
        layeredpane.revalidate();
        hideTitleScreen();
        showTitleScreen();
    }
    
    public void showCreditScreen() {
        layeredpane.add(creditpanel, Integer.valueOf(20000));
        layeredpane.revalidate();
    }
    
    public void hideTitleScreen() {
        layeredpane.remove(titlepanel);
        layeredpane.revalidate();
    }
    
    public void showTitleScreen() {
        layeredpane.add(titlepanel, Integer.valueOf(19000));
        layeredpane.revalidate();
    }
    
    public void hideInventory() {
        layeredpane.remove(invpanel);
        layeredpane.revalidate();
    }
    
    public void showInventory() {
        invpanel.prepare(aventure.getJoueur(),-1);
        layeredpane.add(invpanel, JLayeredPane.POPUP_LAYER);
        layeredpane.revalidate();
    }
    
    public void hideGameOver() {
        layeredpane.remove(gameoverpanel);
        layeredpane.revalidate();
    }
    
    public void showGameOver() {
        layeredpane.add(gameoverpanel, Integer.valueOf(10000));
        layeredpane.revalidate();
    }
    
    public boolean jeterObjet(int index, int pindex) {
        Personnage p = this.aventure.getJoueur();
        if (p==null) return true;
        boolean d = p.jeter(index);
        invpanel.prepare(p,d?-1:pindex);
        layeredpane.revalidate();
        return d;
    }
    
    public boolean detruireObjet(int index, int pindex) {
        Personnage p = this.aventure.getJoueur();
        if (p==null) return true;
        boolean d = p.detruire(index);
        invpanel.prepare(p,d?-1:pindex);
        layeredpane.revalidate();
        return d;
    }
    
    public boolean utiliserObjet(int index, int pindex) {
        Personnage p = this.aventure.getJoueur();
        if (p==null) return true;
        boolean d = p.utiliser(index);
        invpanel.prepare(p,d?-1:pindex);
        layeredpane.revalidate();
        return d;
    }
    
    public boolean reparerObjet(int index, int pindex) {
        Personnage p = this.aventure.getJoueur();
        if (p==null) return true;
        boolean d = p.reparer(index);
        invpanel.prepare(p,d?-1:pindex);
        layeredpane.revalidate();
        return d;
    }
    
    
    public void recommencer() {
        this.aventure.recommencerNiveau();
        this.hideInventory();
        this.hideGameOver();
        combatGraphique22.setVisible(false);
    }
    
    public void abandonner() {
        this.hideInventory();
        this.hideGameOver();
        combatGraphique22.setVisible(false);
        this.aventure.pause();
        this.aventure.restart();
        this.showTitleScreen();
    }
    
    public void startGame() {
        this.aventure.generateNiveau(7);
        this.hideInventory();
        this.hideGameOver();
        combatGraphique22.setVisible(false);
        this.hideTitleScreen();
    }
    
    public String onActionJoueur(String action,Personnage ennemie) {
        String logs = "";
        if(action.equals("attaque")){
            logs+="Tour de "+this.aventure.getJoueur()+":\n";
            logs+=this.aventure.getJoueur().attaque(ennemie);
            this.mettreAJourStatistiquePerso();
            this.mettreAJourListeMonstre();
            this.aventure.getJoueur().actionEffetFinDuTour();
        }
        logs+="Tour des monstres :\n";
        Lieu lieu = this.aventure.getJoueur().getPieceActuel();
        List<Personnage> monstres = lieu.getMonstres();
        int[] monstresMort = new int[monstres.size()];
        int i=0;
        for (Personnage monstre : monstres) {
            if(monstre.getAjoute(PropertyList.PV)!=0){
                logs += monstre.attaque(this.aventure.getJoueur());
            }else{
                monstresMort[i] = monstres.indexOf(monstre);
                logs += monstre.getNom()+" est mort\n";
                i++;
            }
        }
        logs += "Fin du tour\n\n";
        
        for (int j=0;j<i;j++) {
            if(!(monstres.remove(j).getNom().equals("Cadavre"))){
                //ArrayList<Objet> listeloot = new ArrayList<Objet>();
                //listeloot.add(new Nourriture("chaire putidre", "il est d??conseiller de la manger", 5,new Effet("","",-2, 0, 0, 3, new Lieu("Rien"), 0)));
                //monstres.add(new Personnage("Cadavre","une d??pouille inutile",0,0,0,1,listeloot));
            }
        }
        return logs;
    }
    
    private void mettreAJourTout() {
        mettreAJourStatistiquePerso();
        mettreAJourListeMonstre();
        mettreAJourListeObjet();
        mettreAJourSalle();
        mettreAJourListePorte();
        mettreAJourListeInventaire();
    }
    
    private void mettreAJourStatistiquePerso(){
        this.mettreAJourAgilite();
        this.mettreAJourArmeEnMain();
        this.mettreAJourArmure();
        this.mettreAJourForce();
        this.mettreAJourNom();
        this.mettreAJourPv();
        this.mettreAJourListeInventaire();
        this.mettreAJourListeEffet();
    }
    
    private void mettreAJourListeEffet(){
        DefaultListModel<Effet> g = new DefaultListModel<>();
        g.addAll(this.aventure.getJoueur().getEffetCourant());
        //listeEffetJoueur.setModel(g);
    }
    
    private void mettreAJourListeMonstre(){
        DefaultListModel<Personnage> g = new DefaultListModel<>();
        g.addAll(this.aventure.getJoueur().getPieceActuel().getMonstres());
        //listeMonstre.setModel(g);
    }
    
    private void mettreAJourListeObjet(){
        DefaultListModel<Objet> g = new DefaultListModel<>();
        g.addAll(this.aventure.getJoueur().getPieceActuel().getObjets());
        //listeObjet.setModel(g);
    }
    
    private void mettreAJourSalle(){
        canvas1.repaint();
        //jTextPane1.setText(this.aventure.getJoueur().getPieceActuel().toString());
    }
    
    private void mettreAJourListePorte() {
        DefaultListModel<Porte> g = new DefaultListModel<>();
        g.addAll(this.aventure.getJoueur().getPieceActuel().getListePorte());
        //listePortes.setModel(g);
    }
    
    private void mettreAJourAgilite(){
        if(this.aventure.getJoueur().getEffet(PropertyList.AGILITE)!= 0){
            //jTextPaneAgilite.setText(this.aventure.getJoueur().getAjoute(PropertyList.AGILITE)+" + "+this.aventure.getJoueur().getEffet(PropertyList.AGILITE));
        }else{
            //jTextPaneAgilite.setText(this.aventure.getJoueur().getAjoute(PropertyList.AGILITE)+"");
        }
    }
    
    private void mettreAJourArmeEnMain(){
        Equipement main = this.aventure.getJoueur().getMain();
        if (main != null) {
            //textArmeEnMain.setText(main.toString());
        } else {
           // textArmeEnMain.setText("VIDE");
        }
    }
    
    private void mettreAJourArmure(){
        Equipement armure = this.aventure.getJoueur().getArmure();
        if (armure != null) {
            //textArmure.setText(armure.toString());
        } else {
            //textArmure.setText("VIDE");
        }
    }
    
    private void mettreAJourPv(){
        //jTextPanePv.setText(this.aventure.getJoueur().getAjoute(PropertyList.PV)+"");
    }
    
    private void mettreAJourNom(){
        //jTextPaneNom.setText(this.aventure.getJoueur().getNom());
    }
    
    private void mettreAJourForce(){
        //jTextPaneForce.setText(this.aventure.getJoueur().getAjoute(PropertyList.FORCE)+"");
        if(this.aventure.getJoueur().getEffet(PropertyList.FORCE)!= 0){
            //jTextPaneForce.setText(this.aventure.getJoueur().getAjoute(PropertyList.FORCE)+" + "+this.aventure.getJoueur().getEffet(PropertyList.FORCE));
        }else{
            //jTextPaneForce.setText(this.aventure.getJoueur().getAjoute(PropertyList.FORCE)+"");
        }
    }
    
    private void mettreAJourListeInventaire(){
        DefaultListModel<Objet> g = new DefaultListModel<>();
        g.addAll(this.aventure.getJoueur().getInventaire());
        //listeObjetInventaire.setModel(g);
        //jTextPaneInventaire.setText("Inventaire (" + this.aventure.getJoueur().getPoidsInventaire() + "/" + this.aventure.getJoueur().getAjoute(PropertyList.POIDSMAX)+")");
    }
    
    private void onSelectObjetInventaire() {
        /*Objet obj = listeObjetInventaire.getSelectedValue();
        if (obj == null) return;
        
        if (obj instanceof Equipement) {
            if (this.aventure.getJoueur().getMain()==obj) {
                utiliserBouton.setText("D??s??quiper");
            } else {
                utiliserBouton.setText("Equiper");
            }
        } else if (obj.peutUtiliser()) {
            utiliserBouton.setText("Utiliser");
        }*/
    }
    
    private void affichageLogs(String t){
        //logger.setText(logger.getText()+t);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 657, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 478, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    /*****public static void main(String args[]) {*****/
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        /*********try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AppliGraphique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppliGraphique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppliGraphique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppliGraphique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*******/
        //</editor-fold>

        /* Create and display the form */
        /****java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AppliGraphique().setVisible(true);
            }
        });
    }*****/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    private Canvas canvas1;
    private CombatGraphique2 combatGraphique22;
    private JLayeredPane layeredpane;


    private class MyDispatcher implements KeyEventDispatcher {

        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                canvas1.onAppuiTouche(e);
            } else if (e.getID() == KeyEvent.KEY_RELEASED) {
                canvas1.onRelacheTouche(e);
            }
            return false;
        }
    }

}
