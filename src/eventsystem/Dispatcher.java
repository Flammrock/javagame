/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventsystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class Dispatcher implements Serializable {
    
    private final List<SimpleListener> listeners;
    
    public Dispatcher() {
        this.listeners = new ArrayList<>();
    }
    
    public void addListener(SimpleListener listener) {
        this.listeners.add(listener);
    }
    
    public boolean removeListener(SimpleListener listener) {
        return this.listeners.remove(listener);
    }
    
    public SimpleListener removeListener(int index) {
        return this.listeners.remove(index);
    }
    
    public void clear() {
        this.listeners.clear();
    }
    
    public void fireEvent(String type, Object sender, SimpleEvent e) {
        for (SimpleListener listener : this.listeners) {
            if (listener.getType().equals(type)) {
                listener.onEvent(sender, e);
            }
        }
    }
    
}
