package generics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Client {
    public static void main(String[] args) {

//        we cannot use primitive data types in generics
//        Raw Data Type
        Pair p = new Pair();
        p.first="India";
        p.second=100000;
        p.first=5000;

       Pair<String,Integer> p2 = new Pair<String,Integer>();
       Pair<String,Integer> p3= new Pair</*optional*/>();

       p2.setA("India");
//       Integer temp = p2.getB();

        HashMap hm = new HashMap();
        hm.put("Ram","Sham");
        hm.put(10,20);

        p2.doSomething("Aman");

        p2.<Integer>doesSomething(1000);
        p2.doesSomething("Koel");

//      Backward Compatibility

        p2.doSomething2(1000);

//        Can we use class level generics in static methods? No  but we can override using Method level generics


        List<Animal>animals = new ArrayList<>();

        Util.doSomething(animals);

        List<Dog>dogs= new ArrayList<>();

        Util.doSomething(dogs);
        Util.doSomething2(dogs);






    }
}
