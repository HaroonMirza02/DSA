/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mazegenerator;

public class Stack {
 
    private class Node {
 
        Cell data; 
        Node link; 
    }
    Node top;
    Stack() { this.top = null; }
 
    public void push(Cell x)
    {
        Node temp = new Node();
        if (temp == null) {
            System.out.print("\nHeap Overflow");
            return;
        }
 
        temp.data = x;
 
        temp.link = top;
 
        top = temp;
    }
    public boolean isEmpty() { return top == null; }
 
    public Cell peek()
    {
        if (!isEmpty()) {
            return top.data;
        }
        else {
            return null;
        }
    }
 
    public Cell pop() 
    {
        Cell data = top.data;
        if (top == null) {
            return null;
        }
        
        top = (top).link;
        return data;
    }
}
