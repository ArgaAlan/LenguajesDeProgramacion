
package main.java.com.tec.plfinalproject;

import java.util.ArrayList;
import javax.swing.JProgressBar;

public class ProducerConsumer {
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
    
    public static void main(int nProducers, int msProducers, int nConsumers, int msConsumers, int bufferSize, int nRangeInt, int mRangeInt, javax.swing.JProgressBar JProgressBar, javax.swing.JLabel JLabel) {
        
        buffer = new Buffer(bufferSize);
        
        maxSize = buffer.getMaxSize();
        taskCompleted = 0;
        JProgressBar.setValue(0);
        //System.out.println(oneTask);
        JProgressBarS = JProgressBar;
        JLabelTasks = JLabel;
        
        producer = new ArrayList<Producer>();
        consumer = new ArrayList<Consumer>();
        
        for (int i = 0; i < nProducers; i++) {
            producer.add(new Producer(buffer, msProducers, nRangeInt, mRangeInt, i));
            producer.get(i).start();
        }
        
        for (int i = 0; i < nConsumers; i++) {
            consumer.add(new Consumer(buffer, msConsumers, i));
            consumer.get(i).start();
        }
    }
    
    public static void addToCounter() {
        ++taskCompleted;
        JLabelTasks.setText(Integer.toString(taskCompleted));
    }
    
    public static void completedTask(){
        
        JProgressBarS.setValue((int) Math.round((100/maxSize)*buffer.getCurrent()));                        
        Buffer.print("Task completed");
    }
    
    public static int getValueFromExp(String expression) {
        String[] data = expression.split(" ");
        String op = data[1];
        int first = Integer.parseInt(data[2]);
        int second = Integer.parseInt(data[3]);
        switch(op) {
            case "+":
                return first+second;
            case "-":
                return first-second;
            case "*":
                return first*second;
            case "/":
                return first/second;
            default:
                return 1;
        }
    }
    
}
