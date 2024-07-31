/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mazegenerator;
 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
 
import javax.swing.JFrame;
import javax.swing.JPanel;
 
 
public class MazeGenerator extends JFrame {
    Scanner obj = new Scanner(System.in);
  
  private int rows;
    private int cols;
    private Cell[][] Maze;
    

    private MazeHeader mazeheader = new MazeHeader("Maze Game");
    private JPanel mazePanel = new JPanel();
  
  private int row = 0;
    private int col = 0;
    private int endRow;
    private int endCol;
    static int steps = 0;


    public MazeGenerator(int n) throws InterruptedException {
        this.rows = n;
        this.cols = n;     
        this.endRow = rows - 1;
        this.endCol = cols - 1;
        initGUI();
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
private void initMaze() throws InterruptedException {    
        
        mazePanel.setLayout(new GridLayout(rows, cols));

        Maze = new Cell[rows][cols];

        for(int r=0; r<rows; r++) {
            for(int c=0; c<cols; c++) {
                Maze[r][c] = new Cell(r, c);
                mazePanel.add(Maze[r][c]);
            }
        }
           generateMaze();
               row = 0;
		col = 0;
		endRow = rows - 1;
		endCol = cols - 1;
		Maze[row][col].setCurrent(true);
		Maze[endRow][endCol].setEnd(true);

           

}
  private void generateMaze() throws InterruptedException {
        ArrayList<Cell> SaveForLater = new ArrayList<Cell>();
        int totalCells = rows*cols;
        int visitedCells = 1;

    Random rand = new Random();
    int r = rand.nextInt(rows);
    int c = rand.nextInt(cols);

    while (visitedCells < totalCells) {
            LinkedList neighbors = new LinkedList();
            if (isAvailable(r-1, c)) {
                neighbors.add(Maze[r-1][c]);
            }
            if (isAvailable(r+1, c)) {
                neighbors.add(Maze[r+1][c]);
            }
            if (isAvailable(r, c-1)) {
                neighbors.add(Maze[r][c-1]);
            }
            if (isAvailable(r, c+1)) {
                neighbors.add(Maze[r][c+1]);
            }   

      if (neighbors.getSize()>0) {
        
                if (neighbors.getSize()>1) {
                    SaveForLater.add(Maze[r][c]);
                }

                int random = rand.nextInt(neighbors.getSize());
                Cell neighbor = neighbors.get(random);
                Maze[r][c].openTo(neighbor);

        r = neighbor.getRow();
        c = neighbor.getCol();
        visitedCells++;
        

      }      
      else {
        Cell nextCell = SaveForLater.get(0);
        SaveForLater.remove(0);
                r = nextCell.getRow();
                c = nextCell.getCol();
                

      }
      
    }  
    
  }


      private void initGUI() throws InterruptedException {
    add(mazeheader, BorderLayout.PAGE_START);
 
 
     JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.BLACK);
        add(centerPanel, BorderLayout.CENTER);

         initMaze();
        centerPanel.add(mazePanel);


        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        add(buttonPanel, BorderLayout.PAGE_END);
        
        JButton initMazeButton = new JButton("Solve By DFS");
        initMazeButton.setFocusable(false);
        initMazeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DFS();
                Maze[0][0].setCurrent(false);
                Maze[endRow][endCol].setCurrent(true);
            }
        });
        JButton initMazeButton2 = new JButton("Solve By BFS");
        initMazeButton2.setFocusable(false);
        initMazeButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BFS();
                Maze[0][0].setCurrent(false);
                Maze[endRow][endCol].setCurrent(true);
            }
        });
    buttonPanel.add(initMazeButton);
    buttonPanel.add(initMazeButton2);

        addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            char c = e.getKeyChar();
            System.out.println(c);
           int keyCode = e.getKeyCode();            
            moveBall(keyCode);

        }
    });

  }
  private void moveBall(int direction) {
    switch (direction) {
    case KeyEvent.VK_UP :
if (!Maze[row][col].isWall(Cell.TOP)) {
                moveTo(row-1, col, Cell.TOP, Cell.BOTTOM);
                while (!Maze[row][col].isWall(Cell.TOP)
                        && Maze[row][col].isWall(Cell.LEFT)
                        && Maze[row][col].isWall(Cell.RIGHT)) {
                    moveTo(row-1, col, Cell.TOP, Cell.BOTTOM);
                }           }
            break;
        case KeyEvent.VK_DOWN :
            if (!Maze[row][col].isWall(Cell.BOTTOM)) {
                moveTo(row+1, col, Cell.BOTTOM, Cell.TOP);
                while (!Maze[row][col].isWall(Cell.BOTTOM)
                        && Maze[row][col].isWall(Cell.LEFT)
                        && Maze[row][col].isWall(Cell.RIGHT)) {
                    moveTo(row+1, col, Cell.BOTTOM, Cell.TOP);
                }
            }
            break;
        case KeyEvent.VK_LEFT :
            if (!Maze[row][col].isWall(Cell.LEFT)) {
                moveTo(row, col-1, Cell.LEFT, Cell.RIGHT);
                while (!Maze[row][col].isWall(Cell.LEFT)
                        && Maze[row][col].isWall(Cell.TOP)
                        && Maze[row][col].isWall(Cell.BOTTOM)) {
                    moveTo(row, col-1, Cell.LEFT, Cell.RIGHT);
                }
            }
            break;
        case KeyEvent.VK_RIGHT :
            if (!Maze[row][col].isWall(Cell.RIGHT)) {
                moveTo(row, col+1, Cell.RIGHT, Cell.LEFT);
                while (!Maze[row][col].isWall(Cell.RIGHT)
                        && Maze[row][col].isWall(Cell.TOP)
                        && Maze[row][col].isWall(Cell.BOTTOM)) {
                    moveTo(row, col+1, Cell.RIGHT, Cell.LEFT);
                }
      }
      break;
    }
    if (row==endRow && col==endCol) {
                new End().setVisible(true);
    }

  }
  
  private void moveTo(int nextRow, int nextCol,  int firstDirection, int secondDirection) {
    Maze[row][col].setCurrent(false);
    Maze[row][col].addPath(firstDirection);
    row = nextRow;
    col = nextCol;
    Maze[row][col].setCurrent(true);
    Maze[row][col].addPath(secondDirection);
    steps++;
  }

       private boolean isAvailable(int r, int c) {
        boolean available = false;
        if (r>=0 && r<rows && c>=0 && c<cols
                && Maze[r][c].hasAllWalls()) {
            available = true;
        }
        return available;
    }
       boolean isVisited(int row, int col){
           if(Maze[row][col].getVisited() == true){
               return true;
           }
           else
           return false;
       }

     void DFS(){
        Stack VisitNext = new Stack();
        Cell start = Maze[0][0];
        start.setCurrent(true);
        start.setVisited(true);
        Cell finish = Maze[endRow][endCol];
        finish.setEnd(true);
        VisitNext.push(start);
        Cell current = start;
        boolean done = false;
        while(!VisitNext.isEmpty() && !done){
           ;
            if(finish == current){
                new End().setVisible(true);
                done = true;
                break;
            }
            else{
                if(!current.isWall(0) && !isVisited(current.row-1, current.col)){
                    System.out.println("Running Top");
                    Maze[current.row-1][current.col].setVisited(true);
                    moveTo(current.row-1, current.col,Cell.TOP,Cell.BOTTOM );                    
                    VisitNext.push(Maze[current.row-1][current.col]);
                }
                if(!current.isWall(1) && !isVisited(current.row, current.col+1)){
                    System.out.println("Running Right");
                    Maze[current.row][current.col+1].setVisited(true);
                    moveTo(current.row, current.col+1,Cell.RIGHT,Cell.LEFT );                    
                    VisitNext.push(Maze[current.row][current.col+1]);
                }
                if(!current.isWall(2) && !isVisited(current.row+1, current.col)){
                    System.out.println("Running Bottom");
                    Maze[current.row+1][current.col].setVisited(true);     
                    moveTo(current.row+1, current.col,Cell.BOTTOM,Cell.TOP );
                    VisitNext.push(Maze[current.row+1][current.col]);
                }
                if(!current.isWall(3) && !isVisited(current.row, current.col-1)){
                    System.out.println("Running Left");
                    Maze[current.row][current.col-1].setVisited(true);  
                    moveTo(current.row, current.col-1,Cell.LEFT,Cell.RIGHT );
                    VisitNext.push(Maze[current.row][current.col-1]);
                }
                else{
                    current = VisitNext.pop();
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MazeGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }

        }
    }
                }
            
        
    
    private void BFS(){
        Queue VisitNext = new Queue();
        Cell start = Maze[0][0];
        start.setCurrent(true);
        start.setVisited(true);
        Cell finish = Maze[endRow][endCol];
        finish.setEnd(true);
        VisitNext.enqueue(start);
        boolean done = false;
        while(!done && !VisitNext.isEmpty()){ 
            Cell current = VisitNext.peek();
                          

            if(current == finish){
                new End().setVisible(true);
                
               done = true;
               break;
            }
            else{
                if(!current.isWall(0) && !isVisited(current.row-1, current.col)){
                    System.out.println("Running Top");
                    VisitNext.enqueue(Maze[current.row-1][current.col]);
                    moveTo(current.row-1, current.col,Cell.TOP,Cell.BOTTOM ); 
                    Maze[current.row-1][current.col].setVisited(true);
                }
                if(!current.isWall(1) && !isVisited(current.row, current.col+1)){
                    System.out.println("Running Right");
                    VisitNext.enqueue(Maze[current.row][current.col+1]);
                    moveTo(current.row, current.col+1,Cell.RIGHT,Cell.LEFT );  
                    Maze[current.row][current.col+1].setVisited(true);
                }
                if(!current.isWall(2) && !isVisited(current.row+1, current.col)){
                    System.out.println("Running Bottom");
                    VisitNext.enqueue(Maze[current.row+1][current.col]);
                    moveTo(current.row+1, current.col,Cell.BOTTOM,Cell.TOP );
                    Maze[current.row+1][current.col].setVisited(true);
                }
                if(!current.isWall(3) && !isVisited(current.row, current.col-1)){
                    System.out.println("Running Left");
                    VisitNext.enqueue(Maze[current.row][current.col-1]);
                    moveTo(current.row, current.col-1,Cell.LEFT,Cell.RIGHT );
                    Maze[current.row][current.col-1].setVisited(true);
                }
                                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MazeGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }

                
            }
            VisitNext.dequeue();
        }
                }
            
        
    

    
  public static void main(String[] args) throws InterruptedException {
      MazeGenerator M = new MazeGenerator(20);
      M.DFS();
      }
      
             
}     

    
  
      

 
  
