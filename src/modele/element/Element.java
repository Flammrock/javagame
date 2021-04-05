
package modele.element;


public abstract class Element {
    String description;
   
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.getDescription(); //To change body of generated methods, choose Tools | Templates.
    }
    

    
    
}
