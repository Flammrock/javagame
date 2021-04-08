/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canvas;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Animation {
    
    String nom;
    ArrayList<Integer> keyMap;
    int index;
    boolean playing;
    int tick;
    int tickMax;
    int key;
    
    /**
     * Constructeur d'une Animation
     * @param nom le nom de l'animation (e.g. "Idle", "Running", ...etc)
     * @param keyMap les clés d'animation
     */
    public Animation(String nom, ArrayList<Integer> keyMap) {
        this.nom = nom;
        this.keyMap = keyMap;
        this.playing = true;
        
        this.reset();
    }
    
    /**
     * Constructeur d'une Animation
     * @param nom le nom de l'animation (e.g. "Idle", "Running", ...etc)
     * @param keyMap les clés d'animation
     */
    public Animation(String nom, int[] keyMap) {
        this.nom = nom;
        for (int i = 0; i < keyMap.length; i++) {
            this.keyMap.add(keyMap[i]);
        }
        
        this.reset();
    }
    
    private void reset() {
        this.index = 0;
        this.tick = 0;
        this.tickMax = 10;
        this.key = 0;
        this.playing = true;
    }

    /**
     * Permet de récupérer le nom de l'animation
     * @return retourne le nom de l'animation
     */
    public String getNom() {
        return nom;
    }
    
    /**
     * Permet de récupérer la clé d'animation en cours
     * @return retourne la clé d'animation
     */
    public int getKey() {
        return this.key;
    }

    /**
     * Permet de définit la clé d'animation
     * @param key la clé d'animation
     */
    public void setKey(int key) {
        this.key = key;
    }

    /**
     * Permet de récupérer la liste des clés d'animations
     * @return retourne les clés d'animations
     */
    public ArrayList<Integer> getKeyMap() {
        return keyMap;
    }

    /**
     * Permet de définir une autre liste de clé d'animations
     * @param keyMap la nouvelle liste qui contient les nouvelles clés d'animations
     */
    public void setKeyMap(ArrayList<Integer> keyMap) {
        this.keyMap = keyMap;
    }
    
    /**
     * Permet de définir une autre liste de clé d'animations
     * @param keyMap la nouvelle liste qui contient les nouvelles clés d'animations
     */
    public void setKeyMap(int[] keyMap) {
        this.keyMap.clear();
        for (int i = 0; i < keyMap.length; i++) {
            this.keyMap.add(keyMap[i]);
        }
    }
    
    /**
     * Permet de savoir si l'animation est en train d'être jouer
     * @return retourne true si l'animation est en train de jouer, sinon false
     */
    public boolean isPlaying() {
        return this.playing;
    }
    
    /**
     * Permet de jouer l'animation
     */
    public void Play() {
        this.playing = true;
    }
    
    /**
     * Permet de stopper une animation
     */
    public void Stop() {
        this.playing = false;
    }
    
    /**
     * Permet de passer à la prochaine clé
     */
    public void nextKey() {
        if (this.keyMap.isEmpty()) return;
        if (!this.isPlaying()) return;
        
        this.tick++;
        if (this.tick < this.tickMax) return;
        
        this.tick = 0;
        
        this.index++;
        
        if (this.index < 0) this.index = this.keyMap.size()-1;
        if (this.index > this.keyMap.size()-1) this.index = 0;
        
        this.key = this.keyMap.get(this.index);
        
        if (this.key < 0) this.key = 0;
    }
    
}
