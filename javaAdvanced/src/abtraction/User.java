package abtraction;

public abstract class User {

    String name;

    abstract void eat();

    abstract void sleep();

    void dance(){
        System.out.println("user is dancing");
    }


}
