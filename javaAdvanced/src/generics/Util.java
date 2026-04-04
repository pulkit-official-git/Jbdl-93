package generics;

import java.util.List;

public class Util {

    public static void doSomething(List<? extends Animal> animalList){

//        animalList.add(new Dog());
//        animalList.add(new Cat());
//        animalList.add(new Animal());

    }

    public static <T extends Animal>void doSomething2(List<T> animalList){

//        animalList.add(new Dog());
//        animalList.add(new Cat());
//        animalList.add(new Animal());

    }
}
