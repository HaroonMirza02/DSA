/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mazegenerator;
public class LinkedList {  
  
    class Node{  
        Cell data;  
        Node next;  
  
        public Node(Cell data) {  
            this.data = data;  
            this.next = null;  
        }  
    }  
    
    int size = 0;
    public Node head = null;  
    public Node tail = null;  
    
    public void add(Cell data) {  
        Node newNode = new Node(data);  
  
        if(head == null) {  
            head = newNode;  
            tail = newNode;  
        }  
        else {  
            tail.next = newNode;  
            tail = newNode;  
        }  
        size++;
    }
    public int getSize(){
        return this.size;
    }
    public Cell get(int index){
        Node temp = head;
        int count = 0;
        while(index != count){
            temp = temp.next;
            count++;
        }
        return temp.data;
    }
  
  
}