
package main.java.com.tec.plfinalproject;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer extends Thread {
    Buffer buffer;
    int msConsumer;
    private boolean exit = false;
    
    Consumer(Buffer buffer, int msConsumer) {
        this.buffer = buffer;
        this.msConsumer = msConsumer;
    }
    
    @Override
    public void run() {
        System.out.println("Running Consumer...");
        char product;
        
        while(!this.exit) {
            product = this.buffer.consume();
            //System.out.println("Consumer consumed: " + product);
            Buffer.print("Consumer consumed: " + product);
            ProducerConsumer.completedTask();
            ProducerConsumer.addToCounter();
            try {
                Thread.sleep(this.msConsumer);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void killThread(){
        exit = true;
    }
}
