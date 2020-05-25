
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
        
        char product;
        
        while(!this.exit) {
            product = products.charAt(r.nextInt(4));
            int numRand1 = r.nextInt(this.mRangeInt - this.nRangeInt + 1) + this.nRangeInt;
            int numRand2 = r.nextInt(this.mRangeInt - this.nRangeInt + 1) + this.nRangeInt;
            
            String expression = "( " + product + " " + numRand1 + " " + numRand2 + ")" ;
            this.buffer.produce(expression, this.idProducer);
            //System.out.println("Producer produced: " + product);
            Buffer.print("ID: " + this.idProducer + " produced: " + product);
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
