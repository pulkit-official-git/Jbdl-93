package EvenOdd;

public class EvenOdd implements Runnable{
    boolean isEven;

    public EvenOdd(boolean isEven) {
        this.isEven = isEven;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(i%2==0 && isEven==true){
                System.out.println(i + " in thread " + Thread.currentThread().getName());
            }
            else if(i%2!=0 && !isEven){
                System.out.println(i + " in thread " + Thread.currentThread().getName());
            }
        }

    }
}
