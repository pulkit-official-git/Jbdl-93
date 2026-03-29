package syncMethod;

import java.util.concurrent.Callable;

public class Adder implements Callable<Integer> {
    Count count;

    public Adder(Count count) {
        this.count = count;
    }

    @Override
    public Integer call() throws Exception {
        for (int i = 1; i <=1000; i++) {
                count.incrementByI(i);

        }
        return count.value;
    }
}
