/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programhw0;

/**
 * This class implements a generic linked list based queue.
 */
public class GenQueue<T> {

    private class Node {

        T value;
        Node next;

        // Node Constructor
        Node(T val, Node n) {
            value = val;
            next = n;
        }
    }

    private Node front ;
    private Node rear ;
    
    public GenQueue(){
        front = null;
        rear = null;
    }

    /**
     * The method enqueue adds a value to the queue.
     *
     * @param s The value to be added to the queue.
     */
    public void enqueue(T s) {
        //if (rear != null)
        if (!empty()) //test if queue is empty
        {
            rear.next = new Node(s, null);
            rear = rear.next;
        } else //queue is empty
        {
            front = rear = new Node(s, null);
        }
    }

    /**
     * The empty method checks to see if the queue is empty.
     *
     * @return true if and only if queue is empty.
     */
    public boolean empty() {
        return front == null;
    }

    /**
     * The method peek returns value at the front of the queue.
     *
     * @return item at front of queue.
     * @excepton EmptyQueueException When the queue is empty.
     */
    public T peek() {
        if (empty()) {
            throw new EmptyQueueException();
        } else {
            return front.value;
        }
    }

    /**
     * The dequeue method removes and returns the item at the front of the
     * queue.
     *
     * @return item at front of queue.
     * @exception EmptyQueueException When the queue is empty.
     */
    public T dequeue() {
        if (empty()) {
            throw new EmptyQueueException();
        } else {
            T value = front.value;
            front = front.next;
            if (front == null) {
                rear = null;
            }
            return value;
        }
    }

    /**
     * The toString method concatenates all strings in the queue to give a
     * string representation of the contents of the queue.
     *
     * @return string representation of this queue.
     */
    public String toString() {
        String str = "";

        // Walk down the list and append all values
        Node p = front;
        while (p != null) {
            str = str + p.value + " ";
            p = p.next;
        }
        return str;
    }

}
