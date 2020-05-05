
package com.tec.plfinalproject;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer {
    
    private char[] buffer;
    private int maxSize;
    private int current;
    
    Buffer(int maxSize) {
        this.buffer = new char[maxSize];
        this.maxSize = maxSize;
        this.current = 0;
    }
    
    synchronized char consume() {
        char product = 0;
        
        if(this.current == 0) {
            try {
                wait(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        product = this.buffer[current-1];
        current--;
        notify();
        
        return product;
    }
    
    synchronized void produce(char product) {
        if(this.current == this.maxSize) {
            try {
                wait(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.buffer[current] = product;
        current++;
        
        notify();
    }
    
    static int count = 1;
    synchronized static void print(String string) {
        System.out.print(count++ + " ");
        System.out.println(string);
    }
    
}
