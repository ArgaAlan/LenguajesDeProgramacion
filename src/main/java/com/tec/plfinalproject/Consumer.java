
package main.java.com.tec.plfinalproject;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer extends Thread {
    Buffer buffer;
    int msConsumer;
    int idConsumer;
    private boolean exit = false;
    
    Consumer(Buffer buffer, int msConsumer, int idConsumer) {
        this.buffer = buffer;
        this.msConsumer = msConsumer;
        this.idConsumer=idConsumer;
    }
    
    @Override
    public void run() {
        System.out.println("Running Consumer...");
        String product;
        
        while(!this.exit) {
            product = this.buffer.consume();
            //System.out.println("Consumer consumed: " + product);
            Buffer.print("ID: " + this.idConsumer +"consumed: " + product);
            ProducerConsumer.completedTask();
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
