package producerConsumer;

import java.util.ArrayList;
import java.util.List;

public class Store {

    Integer maxSize;
    List<Object> items;

    public Store(Integer maxSize) {
        this.maxSize = maxSize;
        items= new ArrayList<>();
    }

    public void produce(){
        this.items.add(new Object());
        System.out.println("Producer produced " + items.size() + " items");
    }

    public void consume(){
        this.items.remove(items.size()-1);
        System.out.println("Consumer consumed " + items.size() + " items");
    }
}
