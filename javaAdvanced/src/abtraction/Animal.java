package abtraction;

public interface Animal {

    String voice="yuhooo";

    public void eat();

    public void sleep();

    public void move();

    default public void breathe(){
        System.out.println("Animal is breathing");
    }

}
