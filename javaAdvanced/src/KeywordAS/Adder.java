package KeywordAS;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;

public class Adder implements Callable<Integer> {
    Count count;

    public Adder(Count count) {
        this.count = count;
    }

    @Override
    public Integer call() throws Exception {
        for (int i = 1; i <=1000; i++) {
            synchronized (count){
                count.value+=i;
            }

        }
        return count.value;
    }
}
