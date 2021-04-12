
package modele.element;

import canvas.Canvas;
import canvas.Drawable;
import java.awt.Graphics;
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
        return this.getDescription();
    }
    
    
    
    
    @Override
    public ArrayList<Drawable> getDrawables() {return null;}
    
    
    @Override
    public int getX() {return 0;}

    @Override
    public void setX(int x) {}

    @Override
    public int getY() {return 0;}

    @Override
    public void setY(int y) {}
    
    
    
    @Override
    public void MoveTo(int x, int y) {}
    
    @Override
    public void MoveBy(int x, int y) {}
    
    
    @Override
    public void draw(Canvas c, Graphics g) {}
    
}
