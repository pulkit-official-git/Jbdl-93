package abtraction;

public class Client {

    public static void main(String[] args) {

        Animal a = new Dog();
        a.breathe();
        a.move();

        Dog d = new Dog();
        d.bark();
        d.breathe();
    }
}
