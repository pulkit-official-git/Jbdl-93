package semaphorePC;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Store {

    Integer maxSize;
//    List<Object> items;
    ConcurrentLinkedDeque<Object> items;

    public Store(Integer maxSize) {
        this.maxSize = maxSize;
        items= new ConcurrentLinkedDeque<>();
    }

    public void produce(){
        this.items.add(new Object());
        System.out.println("Producer produced " + items.size() + " items");
    }

    public void consume(){
        this.items.remove();
        System.out.println("Consumer consumed " + items.size() + " items");
    }
}
