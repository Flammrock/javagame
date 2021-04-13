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
public class SimpleEvent<T> implements Serializable {
    
    private T data;
    
    public T getData() {
        return this.data;
    }
    
    public SimpleEvent(T data) {
        this.data = data;
    }
    
}
