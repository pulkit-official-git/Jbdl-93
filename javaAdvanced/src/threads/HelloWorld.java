package threads;

public class HelloWorld implements Runnable {

    @Override
    public void run() {
        System.out.println("Hello World " + "in thread " + Thread.currentThread().getName());

    }
}
