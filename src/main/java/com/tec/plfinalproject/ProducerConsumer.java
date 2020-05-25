
package main.java.com.tec.plfinalproject;

import java.util.ArrayList;
import javax.swing.JProgressBar;

public class ProducerConsumer {
    
    private static double taskPercentage;
    private static double oneTask;
    private static double maxSize;
    private static int taskCompleted;
    private static javax.swing.JProgressBar JProgressBarS;
    private static javax.swing.JLabel JLabelTasks;
    private static ArrayList<Producer> producer;
    private static ArrayList<Consumer> consumer;
    private static Buffer buffer;

    public static void stop(){
        for (int i = 0; i < producer.size(); i++) {
            producer.get(i).killThread();
        }
        
        for (int i = 0; i < consumer.size(); i++) {
            consumer.get(i).killThread();
        }
        
        System.out.println("Producers and Consumers stopped.");
    }
    
    public static void main(int nProducers, int msProducers, int nConsumers, int msConsumers, int bufferSize, javax.swing.JProgressBar JProgressBar, javax.swing.JLabel JLabel) {
        
        buffer = new Buffer(bufferSize);
        
        
        double producers = nProducers;
        double consumers = nConsumers;
        oneTask = 100 ;
        taskPercentage = 0;
        
        maxSize = buffer.getMaxSize();
        taskCompleted = 0;
        JProgressBar.setValue(0);
        //System.out.println(oneTask);
        JProgressBarS = JProgressBar;
        JLabelTasks = JLabel;
        
        producer = new ArrayList<Producer>();
        consumer = new ArrayList<Consumer>();
        
        for (int i = 0; i < nProducers; i++) {
            producer.add(new Producer(buffer, msProducers));
            producer.get(i).start();
        }
        
        for (int i = 0; i < nConsumers; i++) {
            consumer.add(new Consumer(buffer, msConsumers));
            consumer.get(i).start();
        }
    }
    
    public static void completedTask(){
        ++taskCompleted;
        
        JProgressBarS.setValue((int) Math.round((100/maxSize)*buffer.getCurrent()));
        
        JLabelTasks.setText(Integer.toString(taskCompleted));
        
        Buffer.print("Task completed");
    }
    
}
