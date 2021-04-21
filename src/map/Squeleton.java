/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import canvas.Canvas;
import canvas.Drawable;
import geometry.Box;
import geometry.Direction;
import geometry.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayDeque;
import java.util.ArrayList;


public class Squeleton implements Drawable {
    
    ArrayList<Point> articulations;
    ArrayList<Box> bones;
    ArrayList<Box> ligaments;
    ArrayList<ArrayList<Integer>> ligaments_connections;
    ArrayList<ArrayList<Box>> ligaments_bounds;
    ArrayList<ArrayList<Direction>> ligaments_directions;
    
    // taille des salles
    int maxx;
    int minx;
    int maxy;
    int miny;
    
    int maxnode;
    
    public Squeleton(int maxnode) {
        this.articulations = new ArrayList<>();
        this.bones = new ArrayList<>();
        this.ligaments = new ArrayList<>();
        this.ligaments_connections = new ArrayList<>();
        this.ligaments_bounds = new ArrayList<>();
        this.ligaments_directions = new ArrayList<>();
        
        this.maxnode = maxnode;
        
        maxx = 1000;
        minx = 500;
        
        maxy = 1000;
        miny = 500;
        
    }
    
    public ArrayList<Box> getBones() {
        return bones;
    }
    
    public ArrayList<Box> getLigaments() {
        return ligaments;
    }
    
    public ArrayList<ArrayList<Integer>> getLigamentConnections() {
        return ligaments_connections;
    }
    
    public ArrayList<ArrayList<Box>> getLigamentBounds() {
        return ligaments_bounds;
    }
    
    public ArrayList<ArrayList<Direction>> getLigamentsDirections() {
        return ligaments_directions;
    }
    
    public void generate(int x, int y) {
        Direction local_direction = new Direction();
        
        Point start = new Point(x,y);
        
        // les prochaines points à explorer (exploration en largeur)
        ArrayDeque<Point> file = new ArrayDeque<>();
        ArrayDeque<Direction> file_direction = new ArrayDeque<>();
        ArrayDeque<Point> file_dist = new ArrayDeque<>();
        ArrayDeque<Box> file_pred = new ArrayDeque<>();
        ArrayDeque<Integer> file_index = new ArrayDeque<>();
        
        int i = 0;
        
        // on ajoute le point de départ dans la file
        file.add(start.copy());
        file_direction.add(local_direction.copy());
        file_dist.add(new Point(300,300));
        

        // tant que la file n'est pas vide et que le nombre max de noeud n'est pas dépassé
        while (!file.isEmpty() && i < this.maxnode) {
        
            // on récupère le prochain point à explorer
            Point current = file.removeFirst();
            local_direction = file_direction.removeFirst();
            Point w = file_dist.removeFirst();
            Box pred = null;
            int pred_index = 0;
            if (!file_pred.isEmpty()) {
                pred = file_pred.removeFirst();
                pred_index = file_index.removeFirst();
            }
            
            int wx = w.x;
            int wy = w.y;
            
            int margin = 50;
            int ligament_size = margin;
            
            // on regarde s'il est compatible
            //int wx = (local_direction.isLeft()||local_direction.isRight()) ? dist : (int)Math.floor(Math.random()*(maxx-minx+1)+minx);
            //int wy = (local_direction.isUp()||local_direction.isDown()) ? dist : (int)Math.floor(Math.random()*(maxy-miny+1)+miny);
            Box b = new Box(new Point(current.x-wx/2+margin,current.y-wy/2+margin),new Point(wx-margin*2,wy-margin*2));
            //System.out.println("" + b.position.x + "," + b.position.y + "," + b.size.x + "," + b.size.y);
            //System.out.println("  -> "+local_direction.d+","+wx+","+wy);
            
            Box ligament = null;
            Box bound1 = null;
            Box bound2 = null;
            if (pred != null) {
                Point pos_start = new Point(pred.position.x,pred.position.y);
                pos_start.append(pred.size.x/2, pred.size.y/2);
                pos_start.append(local_direction, pred.size.x/2, pred.size.y/2);
                Point pos_end = current.copy();
                pos_end.append(local_direction, -wx/2+margin, -wy/2+margin);
                if (local_direction.isUp()||local_direction.isLeft()) {
                    Point tmp = pos_start.copy();
                    pos_start = pos_end;
                    pos_end = tmp;
                }
                int w_lig = (int)Math.abs(pos_end.x-pos_start.x);
                int h_lig = (int)Math.abs(pos_end.y-pos_start.y);
                int w_bind = ligament_size;
                int h_bind = ligament_size;
                if (w_lig == 0) {
                    w_lig = ligament_size;
                    pos_start.append(-w_lig/2,0);
                    pos_end.append(-w_lig/2,0);
                }
                if (h_lig == 0) {
                    h_lig = ligament_size;
                    pos_start.append(0,-h_lig/2);
                    pos_end.append(0,-h_lig/2);
                }
                ligament = new Box(pos_start,new Point(w_lig,h_lig));
                //ligament = new Box(pos_end,new Point(20,20));
                Point bpos_start = pos_start.copy();
                Point bpos_end = pos_end.copy();
                if (local_direction.isLeft()) {
                    bpos_start.append(-w_bind, 0);
                }
                if (local_direction.isUp()) {
                    bpos_start.append(0, -h_bind);
                }
                if (local_direction.isRight()) {
                    bpos_start.append(-w_bind, 0);
                }
                if (local_direction.isDown()) {
                    bpos_start.append(0, -h_bind);
                }
                if (local_direction.isUp()||local_direction.isLeft()) {
                    bound2 = new Box(bpos_start,new Point(w_bind,h_bind));
                    bound1 = new Box(bpos_end,new Point(w_bind,h_bind));
                } else {
                    bound1 = new Box(bpos_start,new Point(w_bind,h_bind));
                    bound2 = new Box(bpos_end,new Point(w_bind,h_bind));
                }
            }
            
            // on regarde s'il y a une collision (si oui on quitte) (équivalent à un raycast)
            if (bones.size() > 0) {
                // @TODO: s'il n'y a qu'une seule collision, simplement ajuster la taille de la boite
                if (b.isCollide(bones)) {
                    
                    if (wx - 5 > minx || wy - 5 > miny) {
                        int vx = (int)Math.floor(Math.random()*(wx-minx+1)+minx);
                        int vy = (int)Math.floor(Math.random()*(wy-miny+1)+miny);
                        Point newcurrent = current.copy();
                        newcurrent.append(local_direction,-wx/2,-wy/2);
                        newcurrent.append(local_direction,vx/2,vy/2);
                        file.add(newcurrent.copy());
                        file_direction.add(local_direction.copy());
                        file_dist.add(new Point(vx,vy));
                        file_pred.add(pred);
                        file_index.add(pred_index);
                        //System.out.println(local_direction.d+","+vx+","+vy);
                    }
                    
                    continue;
                }
            }
            
            // on l'ajoute dans le squelette
            this.articulations.add(current.copy());
            this.bones.add(b);
            if (ligament != null) {
                this.ligaments.add(ligament);
                //this.ligaments_bounds.add(bound1);
                //this.ligaments_bounds.add(bound2);
                while (pred_index > this.ligaments_connections.size()-1) {
                    this.ligaments_connections.add(new ArrayList<>());
                }
                this.ligaments_connections.get(pred_index).add(i+1);
                
                while (pred_index > this.ligaments_bounds.size()-1) {
                    this.ligaments_bounds.add(new ArrayList<>());
                }
                this.ligaments_bounds.get(pred_index).add(bound1);
                this.ligaments_bounds.get(pred_index).add(bound2);
                
                while (pred_index > this.ligaments_directions.size()-1) {
                    this.ligaments_directions.add(new ArrayList<>());
                }
                this.ligaments_directions.get(pred_index).add(local_direction);
                
            }
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
                    file_pred.add(b);
                    file_index.add(i-1);
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
        
        /*for (Box b : bones) {
            g.setColor(Color.BLUE);
            g.drawRect(c.toWorldX(b.position.x),c.toWorldY(b.position.y),c.toScale(b.size.x),c.toScale(b.size.y));
        }*/
        /*for (Point p : articulations) {
            g.setColor(Color.YELLOW);
            g.fillArc(c.toWorldX(p.x-50/2),c.toWorldY(p.y-50/2),c.toScale(50),c.toScale(50),0,360);
        }*/
        for (Box b : ligaments) {
            g.setColor(Color.GREEN);
            g.drawRect(c.toWorldX(b.position.x),c.toWorldY(b.position.y),c.toScale(b.size.x),c.toScale(b.size.y));
        }
        
        /*for (Box b : ligaments_bounds) {
            g.setColor(Color.magenta);
            g.drawRect(c.toWorldX(b.position.x),c.toWorldY(b.position.y),c.toScale(b.size.x),c.toScale(b.size.y));
        }*/
    }

    @Override
    public ArrayList<Drawable> getDrawables() {
        return null;
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public void setX(int x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getY() {
        return 0;
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