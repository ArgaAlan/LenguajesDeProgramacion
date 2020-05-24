
package main.java.com.tec.plfinalproject;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer extends Thread {
    Buffer buffer;
    int msProducer;
    private boolean exit = false; 
    
    Producer(Buffer buffer, int msProducer) {
        this.buffer = buffer;
        this.msProducer = msProducer;
    }
    
    @Override
    public void run() {
        System.out.println("Running Producer...");
        String products = "AEIOU";
        Random r = new Random(System.currentTimeMillis());
        char product;
        
        while(!this.exit) {
            product = products.charAt(r.nextInt(5));
            this.buffer.produce(product);
            //System.out.println("Producer produced: " + product);
            Buffer.print("Producer produced: " + product);
            ProducerConsumer.completedTask();
            
            try {
                Thread.sleep(this.msProducer);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void killThread(){
        exit = true;
    }
    
}
