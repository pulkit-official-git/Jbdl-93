package threads;

public class Client {

    public static void doSomething(){
        System.out.println("do "+ Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        doSomething();
        HelloWorld helloWorld = new HelloWorld();
        Thread t1 = new Thread(helloWorld,"My Thread");
        t1.start();
//        helloWorld.run();
    }
}
