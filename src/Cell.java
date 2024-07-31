/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mazegenerator;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
 
public class Cell extends JPanel {
  private static final int SIZE = 30;
  public static final int TOP = 0;
  public static final int RIGHT = 1;
  public static final int BOTTOM = 2;
  public static final int LEFT = 3;
  int row = -1;
  int col = -1;
  private boolean current = false;
  private boolean end = false;
  private boolean[] path = {false, false, false, false};
  private boolean visited = false;
  private boolean[] wall = {true, true, true, true};
  int r = 0; 
  int c = 0;
    public void setVisited(Boolean set){
        this.visited = set;
    }

    public boolean getVisited() {
        return visited;
    }
    
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }
  public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }

 public void addPath(int side) {
        path[side] = true;
        repaint();
    }

    public boolean isWall(int index) {
        return wall[index];
    }
  public boolean hasAllWalls() {
        boolean allWalls = wall[0] && wall[1] && wall[2] && wall[3];
        return allWalls;
    }
 public void removeWall(int w) {
        wall[w] = false;
        repaint();
    }
   public void setCurrent(boolean current) {
        this.current = current;
        repaint();
    }
public void setEnd(boolean end) {
        this.end = end;
        repaint();
    }


  public void openTo(Cell neighbor) {
        if (row<neighbor.getRow()) {
            removeWall(BOTTOM);
            neighbor.removeWall(TOP);
        }
        else if (row>neighbor.getRow()) {
            removeWall(TOP);
            neighbor.removeWall(BOTTOM);
        }
        else if (col<neighbor.getCol()) {
            removeWall(RIGHT);
            neighbor.removeWall(LEFT);
        }
        else if (col>neighbor.getCol()) {
            removeWall(LEFT);
            neighbor.removeWall(RIGHT);
        }
  }

  public void paintComponent(Graphics g)
    {
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, SIZE, SIZE);
    g.setColor(Color.BLACK);

    
     if (isWall(TOP)) {
            g.drawLine(0, 0, SIZE, 0);
        }
        if (isWall(LEFT)) {
            g.drawLine(0, 0, 0, SIZE);
        }

     g.setColor(Color.RED);
        if (path[TOP]) {
            g.drawLine(SIZE/2, 0, SIZE/2, SIZE/2);
        }
        if (path[BOTTOM]) {
            g.drawLine(SIZE/2, SIZE, SIZE/2, SIZE/2);
        }
        if (path[LEFT]) {
            g.drawLine(0, SIZE/2, SIZE/2, SIZE/2);
        }
        if (path[RIGHT]) {
            g.drawLine(SIZE, SIZE/2, SIZE/2, SIZE/2);
        }

        if (current) {
            g.setColor(Color.GREEN);
            g.fillOval(3,  3, SIZE-6, SIZE-6);
        }
        else if (end) {
            g.setColor(Color.RED);
            g.fillRect(3 ,  3, SIZE-6, SIZE-6);
        }   
       
    }
  public Dimension getPreferredSize() {
        Dimension size = new Dimension(SIZE, SIZE);
        return size;
    }

}
