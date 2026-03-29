package ADDERSUBTRACTOR;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;

public class Adder implements Callable<Integer> {
    Count count;
    Lock lock;

    public Adder(Count count,Lock lock) {
        this.count = count;
        this.lock=lock;
    }

    @Override
    public Integer call() throws Exception {
        for (int i = 1; i <=1000; i++) {
            lock.lock();
            count.value+=i;
            lock.unlock();
        }
        return count.value;
    }
}
