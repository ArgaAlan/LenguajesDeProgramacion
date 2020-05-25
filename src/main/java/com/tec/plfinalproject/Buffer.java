
package main.java.com.tec.plfinalproject;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer {
    
    private String[] buffer;
    private int maxSize;
    private int current;
    
    Buffer(int maxSize) {
        this.buffer = new String[maxSize];
        this.maxSize = maxSize;
        this.current = 0;
    }
    
    synchronized String consume() {
        String product = "";
        
        while(this.current == 0) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        product = this.buffer[current-1];
        current--;
        notifyAll();
        
        return product;
    }
    
    synchronized void produce(String product, int idProducer) {
        while(this.current == this.maxSize) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.buffer[current] = product;
        current++;
        
        notifyAll();
    }
    
    static int count = 1;
    synchronized static void print(String string) {
        System.out.print(count++ + " ");
        System.out.println(string);
    }
    
}
