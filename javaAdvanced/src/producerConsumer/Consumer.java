package producerConsumer;

import java.util.concurrent.Semaphore;

public class Consumer implements Runnable{
    Store store;

    public Consumer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {

        while (true){

            synchronized (store) {
                if (store.items.size() > 0) {
                    store.consume();
                }
            }
        }

    }
}
