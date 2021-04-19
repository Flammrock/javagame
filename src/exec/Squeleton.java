/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exec;

import canvas.Canvas;
import canvas.Drawable;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayDeque;
import java.util.ArrayList;


/**
 * @TODO:
 *   - ajouter class Box
 *   - dans la classe Box mettre un méthode collideWith(ArrayList<Box> list)
 *   - 
 */

enum Enum_Direction {
   UP,
   RIGHT,
   DOWN,
   LEFT
}

class Direction {
    Enum_Direction d;
    public Direction(Enum_Direction d) {
        this.d = d;
    }
    public Direction() {
        int d = (int)Math.floor(Math.random()*4);
        switch (d) {
            case 0:
                this.d = Enum_Direction.UP;
                break;
            case 1:
                this.d = Enum_Direction.RIGHT;
                break;
            case 2:
                this.d = Enum_Direction.DOWN;
                break;
            case 3:
                this.d = Enum_Direction.LEFT;
                break;
            default:
                break;
        }
    }
    public boolean isUp() {
        return this.d == Enum_Direction.UP;
    }
    public boolean isRight() {
        return this.d == Enum_Direction.RIGHT;
    }
    public boolean isDown() {
        return this.d == Enum_Direction.DOWN;
    }
    public boolean isLeft() {
        return this.d == Enum_Direction.LEFT;
    }
    public void rotate() {
        if (this.isUp()) this.d = Enum_Direction.RIGHT;
        else if (this.isRight()) this.d = Enum_Direction.DOWN;
        else if (this.isDown()) this.d = Enum_Direction.LEFT;
        else if (this.isLeft()) this.d = Enum_Direction.UP;
    }
    public Direction copy() {
        return new Direction(this.d);
    }
}

class Data {
    String type;
    String getType(){return this.type;}
}

class Point extends Data {

    public int x;
    public int y;
    
    public Point(int x, int y) {
        this.type = "Point";
        this.x = x;
        this.y = y;
    }
    
    public Point copy() {
        return new Point(this.x,this.y);
    }
    
    public void append(int x, int y) {
        this.x += x;
        this.y += y;
    }
    
    public void append(Direction d, int vx, int vy) {
        if (d.isLeft()) {
            this.x -= vx;
        } else if (d.isRight()) {
            this.x += vx;
        } else if (d.isUp()) {
            this.y -= vy;
        } else if (d.isDown()) {
            this.y += vy;
        }
    }

}

class Line extends Data  {

    public Point p1;
    public Point p2;
    
    public Line(Point p1, Point p2) {
        this.type = "Line";
        this.p1 = p1;
        this.p2 = p2;
    }
    
}

class Box extends Data {

    public Point position;
    public Point size;
    
    public Box(Point pos, Point size) {
        this.type = "Box";
        this.position = pos;
        this.size = size;
    }

    /**
     * permet de faire un raycast mais avec des rectangles :)
     * @param list
     * @return retourne true s'il y a une collision, false sinon
     */
    public boolean isCollide(ArrayList<Box> list) {
        for (Box b : list) {
            if (this.position.x < b.position.x + b.size.x &&
               this.position.x + this.size.x > b.position.x &&
               this.position.y < b.position.y + b.size.y &&
               this.position.y + this.size.y > b.position.y) {
                return true;
            }
        }
        return false;
    }
    
}

public class Squeleton extends Data implements Drawable {
    
    ArrayList<Point> articulations;
    ArrayList<Line> bones;
    ArrayList<Box> entropie;
    
    // taille des salles
    int maxx;
    int minx;
    int maxy;
    int miny;
    
    int maxnode;
    
    public Squeleton(int maxnode) {
        this.type = "Squeleton";
        this.articulations = new ArrayList<>();
        this.entropie = new ArrayList<>();
        this.maxnode = maxnode;
        
        maxx = 1000;
        minx = 400;
        
        maxy = 1000;
        miny = 400;
        
    }
    
    
    public void generate(int x, int y) {
        Direction local_direction = new Direction();
        
        Point start = new Point(x,y);
        
        // les prochaines points à explorer (exploration en largeur)
        ArrayDeque<Point> file = new ArrayDeque<>();
        ArrayDeque<Direction> file_direction = new ArrayDeque<>();
        ArrayDeque<Point> file_dist = new ArrayDeque<>();
        
        int i = 0;
        
        // on ajoute le point de départ dans la file
        file.add(start.copy());
        file_direction.add(local_direction.copy());
        file_dist.add(new Point(200,200));
        
        // tant que la file n'est pas vide et que le nombre max de noeud n'est pas dépassé
        while (!file.isEmpty() && i < this.maxnode) {
        
            // on récupère le prochain point à explorer
            Point current = file.removeFirst();
            local_direction = file_direction.removeFirst();
            Point w = file_dist.removeFirst();
            int wx = w.x;
            int wy = w.y;
            
            
            // on regarde s'il est compatible
            //int wx = (local_direction.isLeft()||local_direction.isRight()) ? dist : (int)Math.floor(Math.random()*(maxx-minx+1)+minx);
            //int wy = (local_direction.isUp()||local_direction.isDown()) ? dist : (int)Math.floor(Math.random()*(maxy-miny+1)+miny);
            Box b = new Box(new Point(current.x-wx/2,current.y-wy/2),new Point(wx,wy));
            //System.out.println("" + b.position.x + "," + b.position.y + "," + b.size.x + "," + b.size.y);
            //System.out.println("  -> "+local_direction.d+","+wx+","+wy);
            
            // on regarde s'il y a une collision (si oui on quitte) (équivalent à un raycast)
            if (entropie.size() > 0) {
                // @TODO: s'il n'y a qu'une seule collision, simplement ajuster la taille de la boite
                if (b.isCollide(entropie)) {
                    
                    if (wx - 5 > minx || wy - 5 > miny) {
                        int vx = (int)Math.floor(Math.random()*(wx-minx+1)+minx);
                        int vy = (int)Math.floor(Math.random()*(wy-miny+1)+miny);
                        Point newcurrent = current.copy();
                        newcurrent.append(local_direction,-wx/2,-wy/2);
                        newcurrent.append(local_direction,vx/2,vy/2);
                        file.add(newcurrent.copy());
                        file_direction.add(local_direction.copy());
                        file_dist.add(new Point(vx,vy));
                        //System.out.println(local_direction.d+","+vx+","+vy);
                    }
                    
                    continue;
                }
            }
            
            // on l'ajoute dans le squelette
            this.articulations.add(current.copy());
            this.entropie.add(b);
            i++;
            
            // on ajoute des points à explorer --->
            
            ArrayDeque<Direction> d = new ArrayDeque<>();
            
            double mlkj = 1;
            local_direction = new Direction();
            for (int j = 0; j < 4; j++) {
                if (Math.random() < mlkj) {
                //if (1==1) {
                    // on se déplace dans la direction courrante
                    int vx = (int)Math.floor(Math.random()*(maxx-minx+1)+minx);
                    int vy = (int)Math.floor(Math.random()*(maxy-miny+1)+miny);
                    Point newcurrent = current.copy();
                    newcurrent.append(local_direction,vx/2+wx/2,vy/2+wy/2);
                    file.add(newcurrent.copy());
                    file_direction.add(local_direction.copy());
                    file_dist.add(new Point(vx,vy));
                    //System.out.println(local_direction.d+","+vx+","+vy);
                    //mlkj -= 0.1;
                }
                local_direction.rotate();
            }
            
        }
        
        
    }

    @Override
    public void draw(Canvas c, Graphics g) {
        
        
        //g.setColor(Color.RED);
        //g.fillRect(c.toWorldX(0), c.toWorldY(0), c.toScale(200), c.toScale(200));
        
        for (Box b : entropie) {
            g.setColor(Color.BLUE);
            g.drawRect(c.toWorldX(b.position.x),c.toWorldY(b.position.y),c.toScale(b.size.x),c.toScale(b.size.y));
        }
        for (Point p : articulations) {
            g.setColor(Color.YELLOW);
            g.fillArc(c.toWorldX(p.x-50/2),c.toWorldY(p.y-50/2),c.toScale(50),c.toScale(50),0,360);
        }
    }

    @Override
    public ArrayList<Drawable> getDrawables() {
        return null;
    }

    @Override
    public int getX() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setX(int x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getY() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setY(int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void moveTo(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void moveBy(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDraw() {
        return true;
    }
    
}