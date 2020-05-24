
package main.java.com.tec.plfinalproject;

import javax.swing.JProgressBar;

public class ProducerConsumer {
    
    private static double taskPercentage = 0;
    private static double oneTask;
    private static javax.swing.JProgressBar JProgressBarS;

    public static void main(int nProducers, int msProducers, int nConsumers, int msConsumers, int bufferSize, javax.swing.JProgressBar JProgressBar) {
        
        double producers = nProducers;
        double consumers = nConsumers;
        oneTask = 100 / (producers*5 + consumers*5);
        //System.out.println(oneTask);
        JProgressBarS = JProgressBar;
        
        Buffer buffer = new Buffer(bufferSize);
        
        for (int i = 0; i < nProducers; i++) {
            Producer producer = new Producer(buffer, msProducers);
            producer.start();
        }
        
        for (int i = 0; i < nConsumers; i++) {
            Consumer consumer = new Consumer(buffer, msConsumers);
            consumer.start();
        }
    }
    
    public static void completedTask(){
        taskPercentage += oneTask;
        //Buffer.print(Double.toString(oneTask));
        //Buffer.print(Double.toString(taskPercentage));
        JProgressBarS.setValue((int) Math.round(taskPercentage));
        
        Buffer.print("Task completed");
    }
    
}
