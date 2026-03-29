package producerConsumer;

import java.util.concurrent.Semaphore;

public class Producer implements Runnable{
    Store store;

    public Producer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {

        while (true){

            synchronized (store) {
                if (store.items.size() < store.maxSize) {
                    store.produce();
                }
            }
        }

    }
}
