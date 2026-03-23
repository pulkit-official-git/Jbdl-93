package EvenOdd;

public class Client {

    public static void main(String[] args) {
        EvenOdd e = new EvenOdd(true);
        EvenOdd o = new EvenOdd(false);
        Thread even = new Thread(e,"Even Thread");
        Thread odd = new Thread(o, "Odd Thread");
        even.start();
        odd.start();

    }
}
