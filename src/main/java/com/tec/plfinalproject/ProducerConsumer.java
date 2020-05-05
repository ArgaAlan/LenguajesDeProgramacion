
package com.tec.plfinalproject;

public class ProducerConsumer {

    public static void main(int nProducers, int msProducers, int nConsumers, int msConsumers, int bufferSize) {
        
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
    
}
