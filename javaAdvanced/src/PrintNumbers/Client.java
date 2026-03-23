package PrintNumbers;

public class Client {
    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            PrintNumber printNumber = new PrintNumber(i);
            if(i==80){
                System.out.println(i);
            }
            Thread t1 = new Thread(printNumber,"Thread " + i);
            t1.start();
        }

    }
}
