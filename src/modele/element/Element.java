
package modele.element;

import canvas.Drawable;
import java.io.*;
import java.util.ArrayList;


public abstract class Element implements Serializable, Drawable {
    String description;
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public Element copie() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (Element) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return this.getDescription(); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ArrayList<Drawable> getDrawables() {
        return null;
    }
    
    
    public int getX() {
        return 0;
    }

    public void setX(int x) {}

    public int getY() {
        return 0;
    }

    public void setY(int y) {}
    
    
    
    public void MoveTo(int x, int y) {}
    
    public void MoveBy(int x, int y) {}
    
    public void applyMove() {}
    
    public void cancelMove() {}
    
    public int getMx() {
        return 0;
    }
    
    public int getMy() {
        return 0;
    }
    
}
