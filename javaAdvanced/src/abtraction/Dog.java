package abtraction;

public class Dog implements Animal{

    @Override
    public void eat() {
        System.out.println("Dog eat");
    }

    @Override
    public void sleep() {
        System.out.println("Dog sleep");
    }

    @Override
    public void move() {
        System.out.println("Dog move");
    }

    public void bark(){
        System.out.println("Dog bark bhow bhow");
    }

    @Override
    public void breathe(){
        System.out.println("dog is breathing");
    }
}
