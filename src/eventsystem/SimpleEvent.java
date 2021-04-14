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
public class SimpleEvent implements Serializable {
    
    private Object data;
    
    public Object getData() {
        return this.data;
    }
    
    public SimpleEvent(Object data) {
        this.data = data;
    }
    
}
