/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventsystem;

import java.io.Serializable;

/**
 *
 * @author User
 */
public abstract class SimpleListener implements Serializable {
    
    private String type;
    
    public SimpleListener(String type) {
        this.type = type;
    }
    
    public String getType() {
        return this.type;
    }
    
    public abstract void onEvent(Object sender, SimpleEvent e);
    
}
