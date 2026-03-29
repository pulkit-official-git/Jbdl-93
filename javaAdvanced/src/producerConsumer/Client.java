package producerConsumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Client {

    public static void main(String[] args) {
        int maxSize=5;
        Store store = new Store(maxSize);
        ExecutorService ex = Executors.newCachedThreadPool();


        Producer producer = new Producer(store);
        Consumer consumer = new Consumer(store);

        for(int i=0;i<5;i++){
            ex.submit(producer);
        }

        for(int i=0;i<10;i++){
            ex.submit(consumer);
        }




    }
}
