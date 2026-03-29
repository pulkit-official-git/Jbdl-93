package semaphorePC;

import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Client {

    public static void main(String[] args) {
        int maxSize=5;
        Store store = new Store(maxSize);
        ExecutorService ex = Executors.newCachedThreadPool();

        Semaphore ps = new Semaphore(maxSize);
        Semaphore cs = new Semaphore(0);


        Producer producer = new Producer(store,ps,cs);
        Consumer consumer = new Consumer(store,ps,cs);

        for(int i=0;i<5;i++){
            ex.submit(producer);
        }

        for(int i=0;i<10;i++){
            ex.submit(consumer);
        }




    }
}
