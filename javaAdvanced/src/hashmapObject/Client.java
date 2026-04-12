package hashmapObject;

import java.util.HashMap;

public class Client {
    public static void main(String[] args) {


        HashMap<String,Integer> hm = new HashMap<>();
        hm.put("Apple",1);
        hm.put("Mango",2);
        hm.put("Apple",3);

        System.out.println(hm.size());

        HashMap<Student,String> hm2 = new HashMap<>();
        Student s1 = new Student("Fred",40);
        Student s2 = new Student("skr",35);
        Student s3 = new Student("Fred",40);

        hm2.put(s1,"Engineer");
        hm2.put(s2,"HR");
        hm2.put(s3,"Manager");

        System.out.println(hm2.size());





    }
}
