
package main.java.com.tec.plfinalproject;

import javax.swing.JProgressBar;

public class ProducerConsumer {
    
    private static double taskPercentage;
    private static double oneTask;
    private static int taskCompleted;
    private static javax.swing.JProgressBar JProgressBarS;
    private static javax.swing.JLabel JLabelTasks;

    public static void main(int nProducers, int msProducers, int nConsumers, int msConsumers, int bufferSize, javax.swing.JProgressBar JProgressBar, javax.swing.JLabel JLabel) {
        
        taskCompleted = 0;
        taskPercentage = 0;
        JProgressBar.setValue(0);
        double producers = nProducers;
        double consumers = nConsumers;
        oneTask = 100 / (producers*5 + consumers*5);
        //System.out.println(oneTask);
        JProgressBarS = JProgressBar;
        JLabelTasks = JLabel;
        
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
        ++taskCompleted;
        //Buffer.print(Double.toString(oneTask));
        //Buffer.print(Double.toString(taskPercentage));
        JProgressBarS.setValue((int) Math.round(taskPercentage));
        JLabelTasks.setText(Integer.toString(taskCompleted));
        
        Buffer.print("Task completed");
    }
    
}
