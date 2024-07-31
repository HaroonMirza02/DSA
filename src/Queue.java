/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mazegenerator;
public class Queue
{
 private Node front, rear; 
 private int queueSize; 
  
 private class Node { 
 Cell data;
 Node next;
 }
  
 public Queue()
 {
 front = null;
 rear = null;
 queueSize = 0;
 }
 
 
 public boolean isEmpty()
 {
 return (queueSize == 0);
 }
  
 public Cell dequeue()
 {
 Cell data = front.data;
 front = front.next;
 if (isEmpty()) 
 {
 rear = null;
 
 }
 queueSize--;
 return data;
 }
 public Cell peek(){
     return front.data;
 }
  
 public boolean enqueue(Cell data)
 {
 Node oldRear = rear;
 rear = new Node();
 rear.data = data;
 rear.next = null;
 if (isEmpty()) 
 {
 front = rear;
 }
 else  {
 oldRear.next = rear;
 }
 queueSize++;
 return true;
 }
}
