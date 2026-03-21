package singleton;

public class Temp {

    public void get(){
        Redis single = Redis.getInstance();
        System.out.println(single);
    }
}
