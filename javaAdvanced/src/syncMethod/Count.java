package syncMethod;

public class Count {
    Integer value=0;


    public synchronized void incrementByI(int i){
        value+=i;
    }

    public synchronized void decrementByI(int i){
        value-=i;
    }
}
