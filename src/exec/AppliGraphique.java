/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exec;

import canvas.Combat.DebutCombatEvent;
import eventsystem.SimpleEvent;
import eventsystem.SimpleListener;
import java.awt.Color;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import map.Camera;
import modele.element.*;


/**
 *
 * @author pierrecharbit
 */
public class AppliGraphique extends javax.swing.JFrame {
    
    private Aventure aventure;
    /**
     * Creates new form AppliGraphique
     * @param aventure
     */
    public AppliGraphique(Aventure aventure) {
        this.aventure = aventure;
        initComponents();

        AppliGraphique _this = this;
        listeObjetInventaire.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    _this.onSelectObjetInventaire();
                }
            }
        });
        
        
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
        (int)(1000 / 30));
        

        mettreAJourTout();
        
        this.aventure.addListerner(new SimpleListener("onCombatCommence"){
            //@override
            public void onEvent(Object sender, SimpleEvent e) {
                Aventure aventure = (Aventure) sender;
                DebutCombatEvent p = (DebutCombatEvent)e;
                Personnage perso1 = p.getPerso1();
                Personnage perso2 = p.getPerso2();
                combatGraphique22.bindAventure(aventure);
                combatGraphique22.lancerCombat(perso1,perso2);
                combatGraphique22.setVisible(true);
                
            }
        });
    
        this.aventure.addListerner(new SimpleListener("onCombatTermine"){
            //@override
            public void onEvent(Object sender, SimpleEvent e) {
                combatGraphique22.setVisible(false);
                Aventure aventure = (Aventure) sender;
                DebutCombatEvent p = (DebutCombatEvent)e;
                Personnage perso2 = p.getPerso2();
                aventure.getJoueur().getPieceActuel().kill(perso2);
            }
        });
        
        Combattre.setVisible(false);
        allerDansPorte.setVisible(false);
        //canvas1.setVisible(false);
        jPanel1.setVisible(false);
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
        utiliserBouton.setVisible(false);
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
                //listeloot.add(new Nourriture("chaire putidre", "il est déconseiller de la manger", 5,new Effet("","",-2, 0, 0, 3, new Lieu("Rien"), 0)));
                //monstres.add(new Personnage("Cadavre","une dépouille inutile",0,0,0,1,listeloot));
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
        listeEffetJoueur.setModel(g);
    }
    
    private void mettreAJourListeMonstre(){
        DefaultListModel<Personnage> g = new DefaultListModel<>();
        g.addAll(this.aventure.getJoueur().getPieceActuel().getMonstres());
        listeMonstre.setModel(g);
    }
    
    private void mettreAJourListeObjet(){
        DefaultListModel<Objet> g = new DefaultListModel<>();
        g.addAll(this.aventure.getJoueur().getPieceActuel().getObjets());
        listeObjet.setModel(g);
    }
    
    private void mettreAJourSalle(){
        canvas1.repaint();
        jTextPane1.setText(this.aventure.getJoueur().getPieceActuel().toString());
    }
    
    private void mettreAJourListePorte() {
        DefaultListModel<Porte> g = new DefaultListModel<>();
        g.addAll(this.aventure.getJoueur().getPieceActuel().getListePorte());
        listePortes.setModel(g);
    }
    
    private void mettreAJourAgilite(){
        if(this.aventure.getJoueur().getEffet(PropertyList.AGILITE)!= 0){
            jTextPaneAgilite.setText(this.aventure.getJoueur().getAjoute(PropertyList.AGILITE)+" + "+this.aventure.getJoueur().getEffet(PropertyList.AGILITE));
        }else{
            jTextPaneAgilite.setText(this.aventure.getJoueur().getAjoute(PropertyList.AGILITE)+"");
        }
    }
    
    private void mettreAJourArmeEnMain(){
        Equipement main = this.aventure.getJoueur().getMain();
        if (main != null) {
            textArmeEnMain.setText(main.toString());
        } else {
            textArmeEnMain.setText("VIDE");
        }
    }
    
    private void mettreAJourArmure(){
        Equipement armure = this.aventure.getJoueur().getArmure();
        if (armure != null) {
            textArmure.setText(armure.toString());
        } else {
            textArmure.setText("VIDE");
        }
    }
    
    private void mettreAJourPv(){
        jTextPanePv.setText(this.aventure.getJoueur().getAjoute(PropertyList.PV)+"");
    }
    
    private void mettreAJourNom(){
        jTextPaneNom.setText(this.aventure.getJoueur().getNom());
    }
    
    private void mettreAJourForce(){
        jTextPaneForce.setText(this.aventure.getJoueur().getAjoute(PropertyList.FORCE)+"");
        if(this.aventure.getJoueur().getEffet(PropertyList.FORCE)!= 0){
            jTextPaneForce.setText(this.aventure.getJoueur().getAjoute(PropertyList.FORCE)+" + "+this.aventure.getJoueur().getEffet(PropertyList.FORCE));
        }else{
            jTextPaneForce.setText(this.aventure.getJoueur().getAjoute(PropertyList.FORCE)+"");
        }
    }
    
    private void mettreAJourListeInventaire(){
        DefaultListModel<Objet> g = new DefaultListModel<>();
        g.addAll(this.aventure.getJoueur().getInventaire());
        listeObjetInventaire.setModel(g);
        jTextPaneInventaire.setText("Inventaire (" + this.aventure.getJoueur().getPoidsInventaire() + "/" + this.aventure.getJoueur().getAjoute(PropertyList.POIDSMAX)+")");
    }
    
    private void onSelectObjetInventaire() {
        Objet obj = listeObjetInventaire.getSelectedValue();
        if (obj == null) return;
        
        if (obj instanceof Equipement) {
            if (this.aventure.getJoueur().getMain()==obj) {
                utiliserBouton.setText("Déséquiper");
            } else {
                utiliserBouton.setText("Equiper");
            }
        } else if (obj.peutUtiliser()) {
            utiliserBouton.setText("Utiliser");
        }
    }
    
    private void affichageLogs(String t){
        logger.setText(logger.getText()+t);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        canvas1 = new canvas.Canvas();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jPanel4 = new javax.swing.JPanel();
        allerDansPorte = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        listePortes = new javax.swing.JList<>();
        jPanel5 = new javax.swing.JPanel();
        Combattre = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        listeMonstre = new javax.swing.JList<>();
        jPanel6 = new javax.swing.JPanel();
        ramasser = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        listeObjet = new javax.swing.JList<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane19 = new javax.swing.JScrollPane();
        jTextPaneInventaire = new javax.swing.JTextPane();
        jScrollPane20 = new javax.swing.JScrollPane();
        listeObjetInventaire = new javax.swing.JList<>();
        jeterBouton = new javax.swing.JButton();
        utiliserBouton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextPaneNomFixe = new javax.swing.JTextPane();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextPanePvFixe = new javax.swing.JTextPane();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTextPaneForceFixe = new javax.swing.JTextPane();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTextPaneAgiliteFixe = new javax.swing.JTextPane();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTextPaneArmeEnMainFixe = new javax.swing.JTextPane();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTextPaneArmureFixe = new javax.swing.JTextPane();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTextPaneNom = new javax.swing.JTextPane();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTextPanePv = new javax.swing.JTextPane();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTextPaneForce = new javax.swing.JTextPane();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTextPaneAgilite = new javax.swing.JTextPane();
        jScrollPane21 = new javax.swing.JScrollPane();
        textArmure = new javax.swing.JTextArea();
        jScrollPane22 = new javax.swing.JScrollPane();
        textArmeEnMain = new javax.swing.JTextArea();
        jScrollPane23 = new javax.swing.JScrollPane();
        listeEffetJoueur = new javax.swing.JList<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        logger = new javax.swing.JTextArea();
        combatGraphique22 = new canvas.Combat.CombatGraphique2();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        canvas1.setBackground(new java.awt.Color(0, 0, 0));

        jTextPane1.setEditable(false);
        jScrollPane3.setViewportView(jTextPane1);

        allerDansPorte.setText("Aller");
        allerDansPorte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allerDansPorteActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(listePortes);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(allerDansPorte, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(allerDansPorte)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Combattre.setText("Combattre");
        Combattre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CombattreActionPerformed(evt);
            }
        });

        jScrollPane5.setViewportView(listeMonstre);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Combattre)
                .addGap(311, 311, 311))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Combattre))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ramasser.setText("Ramasser");
        ramasser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ramasserActionPerformed(evt);
            }
        });

        jScrollPane4.setViewportView(listeObjet);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ramasser)
                .addGap(22, 22, 22))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ramasser)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTextPaneInventaire.setEditable(false);
        jTextPaneInventaire.setText("Inventaire:");
        jScrollPane19.setViewportView(jTextPaneInventaire);

        jScrollPane20.setViewportView(listeObjetInventaire);

        jeterBouton.setText("Jeter");
        jeterBouton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jeterBoutonActionPerformed(evt);
            }
        });

        utiliserBouton.setText("Utiliser");
        utiliserBouton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                utiliserBoutonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane19)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jeterBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(utiliserBouton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane20, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jeterBouton)
                    .addComponent(utiliserBouton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jTextPane2.setEditable(false);
        jTextPane2.setText("Feuille de personnage");
        jScrollPane6.setViewportView(jTextPane2);

        jTextPaneNomFixe.setEditable(false);
        jTextPaneNomFixe.setText("Nom");
        jScrollPane7.setViewportView(jTextPaneNomFixe);

        jTextPanePvFixe.setEditable(false);
        jTextPanePvFixe.setText("PV");
        jScrollPane8.setViewportView(jTextPanePvFixe);

        jTextPaneForceFixe.setEditable(false);
        jTextPaneForceFixe.setText("Force");
        jScrollPane9.setViewportView(jTextPaneForceFixe);

        jTextPaneAgiliteFixe.setEditable(false);
        jTextPaneAgiliteFixe.setText("Agilté");
        jScrollPane10.setViewportView(jTextPaneAgiliteFixe);

        jTextPaneArmeEnMainFixe.setEditable(false);
        jTextPaneArmeEnMainFixe.setText("Arme en main");
        jScrollPane11.setViewportView(jTextPaneArmeEnMainFixe);

        jTextPaneArmureFixe.setEditable(false);
        jTextPaneArmureFixe.setText("Armure");
        jScrollPane12.setViewportView(jTextPaneArmureFixe);

        jTextPaneNom.setEditable(false);
        jScrollPane13.setViewportView(jTextPaneNom);

        jTextPanePv.setEditable(false);
        jScrollPane14.setViewportView(jTextPanePv);

        jTextPaneForce.setEditable(false);
        jScrollPane15.setViewportView(jTextPaneForce);

        jTextPaneAgilite.setEditable(false);
        jScrollPane16.setViewportView(jTextPaneAgilite);

        textArmure.setEditable(false);
        textArmure.setColumns(20);
        textArmure.setRows(1);
        jScrollPane21.setViewportView(textArmure);

        textArmeEnMain.setEditable(false);
        textArmeEnMain.setColumns(20);
        textArmeEnMain.setRows(1);
        jScrollPane22.setViewportView(textArmeEnMain);

        jScrollPane23.setViewportView(listeEffetJoueur);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane23, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane7)
                            .addComponent(jScrollPane8)
                            .addComponent(jScrollPane9)
                            .addComponent(jScrollPane10)
                            .addComponent(jScrollPane11)
                            .addComponent(jScrollPane12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane21)
                            .addComponent(jScrollPane13)
                            .addComponent(jScrollPane14)
                            .addComponent(jScrollPane15)
                            .addComponent(jScrollPane16)
                            .addComponent(jScrollPane22, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane22, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(239, 239, 239))
        );

        logger.setEditable(false);
        logger.setBackground(new java.awt.Color(204, 204, 204));
        logger.setColumns(20);
        logger.setRows(5);
        jScrollPane1.setViewportView(logger);

        javax.swing.GroupLayout canvas1Layout = new javax.swing.GroupLayout(canvas1);
        canvas1.setLayout(canvas1Layout);
        canvas1Layout.setHorizontalGroup(
            canvas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(canvas1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(canvas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(canvas1Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 996, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(canvas1Layout.createSequentialGroup()
                        .addGroup(canvas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, canvas1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(canvas1Layout.createSequentialGroup()
                        .addComponent(combatGraphique22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        canvas1Layout.setVerticalGroup(
            canvas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, canvas1Layout.createSequentialGroup()
                .addGroup(canvas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(canvas1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(combatGraphique22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(97, 97, 97)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(canvas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(canvas1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(canvas1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void allerDansPorteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allerDansPorteActionPerformed
        // TODO add your handling code here:
        Porte p = listePortes.getSelectedValue();
        if (p!=null) {
            this.aventure.getJoueur().setPieceActuel(p.getLieu2());
            this.mettreAJourListePorte();
            this.mettreAJourListeObjet();
            this.mettreAJourListeMonstre();
            this.mettreAJourSalle();
        }
    }//GEN-LAST:event_allerDansPorteActionPerformed

    private void CombattreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CombattreActionPerformed
        // TODO add your handling code here:
        Personnage p = listeMonstre.getSelectedValue();
        if (p == null) return;
        affichageLogs(this.onActionJoueur("attaque",p));
        this.mettreAJourTout();
    }//GEN-LAST:event_CombattreActionPerformed

    private void utiliserBoutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_utiliserBoutonActionPerformed
        // TODO add your handling code here:
        int selected = listeObjetInventaire.getSelectedIndex();
        Objet obj = listeObjetInventaire.getSelectedValue();
        if (obj == null) return;
        
        boolean needUpdate = false;
        
        if (obj instanceof Equipement) {
            if (this.aventure.getJoueur().getMain()==obj) {
                needUpdate = this.aventure.getJoueur().desequip(selected);
            } else {
                needUpdate = this.aventure.getJoueur().equip(selected);
            }
        } else if (obj.peutUtiliser()) {
            needUpdate = this.aventure.getJoueur().utiliserObjet(selected);
        }
         
        if (needUpdate) {
            affichageLogs(this.onActionJoueur("equipe",null));
            this.mettreAJourTout();
        }
    }//GEN-LAST:event_utiliserBoutonActionPerformed

    private void ramasserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ramasserActionPerformed
        // TODO add your handling code here:
        int selected = listeObjet.getSelectedIndex();
        if(selected!=-1){
            if(this.aventure.getJoueur().ajouterObjet(this.aventure.getJoueur().getPieceActuel().getObjets().get(selected))){
                this.aventure.getJoueur().getPieceActuel().getObjets().remove(selected);
            }
            mettreAJourTout();
        }
    }//GEN-LAST:event_ramasserActionPerformed

    private void jeterBoutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jeterBoutonActionPerformed
        // TODO add your handling code here:
        int selected = listeObjetInventaire.getSelectedIndex();
        if(selected!=-1){
            if(selected!=this.aventure.getJoueur().getInventaire().indexOf(this.aventure.getJoueur().getMain())){
                this.aventure.getJoueur().getPieceActuel().ajouter(this.aventure.getJoueur().getInventaire().remove(selected));
                mettreAJourTout();
            }
        }
    }//GEN-LAST:event_jeterBoutonActionPerformed

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
    private javax.swing.JButton Combattre;
    private javax.swing.JButton allerDansPorte;
    private canvas.Canvas canvas1;
    private canvas.Combat.CombatGraphique2 combatGraphique22;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JTextPane jTextPaneAgilite;
    private javax.swing.JTextPane jTextPaneAgiliteFixe;
    private javax.swing.JTextPane jTextPaneArmeEnMainFixe;
    private javax.swing.JTextPane jTextPaneArmureFixe;
    private javax.swing.JTextPane jTextPaneForce;
    private javax.swing.JTextPane jTextPaneForceFixe;
    private javax.swing.JTextPane jTextPaneInventaire;
    private javax.swing.JTextPane jTextPaneNom;
    private javax.swing.JTextPane jTextPaneNomFixe;
    private javax.swing.JTextPane jTextPanePv;
    private javax.swing.JTextPane jTextPanePvFixe;
    private javax.swing.JButton jeterBouton;
    private javax.swing.JList<Effet> listeEffetJoueur;
    private javax.swing.JList<Personnage> listeMonstre;
    private javax.swing.JList<Objet> listeObjet;
    private javax.swing.JList<Objet> listeObjetInventaire;
    private javax.swing.JList<Porte> listePortes;
    private javax.swing.JTextArea logger;
    private javax.swing.JButton ramasser;
    private javax.swing.JTextArea textArmeEnMain;
    private javax.swing.JTextArea textArmure;
    private javax.swing.JButton utiliserBouton;
    // End of variables declaration//GEN-END:variables




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
