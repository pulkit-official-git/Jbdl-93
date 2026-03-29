package semaphorePC;

import java.util.concurrent.Semaphore;

public class Producer implements Runnable{
    Store store;
    Semaphore ps;
    Semaphore cs;

    public Producer(Store store, Semaphore ps, Semaphore cs) {
        this.store = store;
        this.ps=ps;
        this.cs=cs;
    }

    @Override
    public void run() {

        while (true){

            try {
                ps.acquire();
                if (store.items.size() < store.maxSize) {
                    store.produce();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            cs.release();


        }

    }
}
