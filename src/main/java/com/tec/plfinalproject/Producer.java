
package main.java.com.tec.plfinalproject;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer extends Thread {
    Buffer buffer;
    int msProducer;
    int nRangeInt;
    int mRangeInt;
    int idProducer;
    private boolean exit = false;
    private static Random r = new Random(System.currentTimeMillis());
    
    Producer(Buffer buffer, int msProducer, int nRangeInt, int mRangeInt, int idProducer) {
        this.buffer = buffer;
        this.msProducer = msProducer;
        this.nRangeInt= nRangeInt;
        this.mRangeInt= mRangeInt;
        this.idProducer=idProducer;
    }
    
    @Override
    public void run() {
        System.out.println("Running Producer...");
        String products = "+-*/";
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
