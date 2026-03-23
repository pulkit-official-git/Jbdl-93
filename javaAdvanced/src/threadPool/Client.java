package threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    public static void main(String[] args) {
//        ExecutorService ex = Executors.newFixedThreadPool(10);
        ExecutorService ex = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000000; i++) {
//            if(i==80){
//                System.out.println(i);
//            }
            PrintNumbers printNumbers = new PrintNumbers(i);
            ex.submit(printNumbers);
        }

    }
}
