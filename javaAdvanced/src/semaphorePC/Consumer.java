package semaphorePC;

import java.util.concurrent.Semaphore;

public class Consumer implements Runnable{
    Store store;
    Semaphore ps;
    Semaphore cs;

    public Consumer(Store store, Semaphore ps, Semaphore cs) {
        this.store = store;
        this.ps = ps;
        this.cs = cs;
    }

    @Override
    public void run() {

        while (true){

            try {
                cs.acquire();
                if (store.items.size() > 0) {
                    store.consume();
                }
            } catch (InterruptedException e) {
//                cs.release();
                throw new RuntimeException(e);
            }
            ps.release();

        }

    }
}
