package collections;

import java.util.*;

public class Client {

    public static void main(String[] args) {
        List<String>nos = new ArrayList<>();
        nos.add("skr");
        nos.add("fred");
        nos.add("encore");
        nos.add("calm");
        System.out.println(nos);

        List<String>nos2 = new LinkedList<>();
        nos2.add("skr");
        nos2.add("fred");
        nos2.add("encore");
        nos2.add("calm");
        System.out.println(nos2);















        Set<String>nos3 = new HashSet<>();
        nos3.add("skr");
        nos3.add("fred");
        nos3.add("encore");
        nos3.add("calm");
        System.out.println(nos3);


        Set<String>nos4 = new TreeSet<>();
        nos4.add("skr");
        nos4.add("fred");
        nos4.add("encore");
        nos4.add("calm");
        System.out.println(nos4);

        Set<String>nos5 = new LinkedHashSet<>();
        nos5.add("skr");
        nos5.add("fred");
        nos5.add("encore");
        nos5.add("calm");
        System.out.println(nos5);

        Set<PaymentMode>pm = EnumSet.of(PaymentMode.ACCEPTED,PaymentMode.REJECTED,PaymentMode.PROCESSING);
        System.out.println(EnumSet.range(PaymentMode.ACCEPTED,PaymentMode.PROCESSING));


//        HashMap<String,Integer>mp = new HashMap<>();
//        mp.put("Ram",1);
//        mp.put("Sham",2);
//        mp.put("Ram",3);

//        HashMap<Student,String>mp2 = new HashMap<>();
////        Student s1 = new Student(1,"RAM");
////        Student s2 = new Student(2,"Sham");
////        Student s3 = new Student(1,"RAM");
//        mp2.put(new Student(1,"RAM"),"SDE");
//        mp2.put(new Student(2,"Sham"),"HR");
//        mp2.put(new Student(1,"RAM"),"Manager");

//        equals,hashcode


        Integer[]arr = {4,5,3,2,1,6,7};

        Arrays.sort(arr, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        for(int i:arr){
            System.out.println(i);
        }

        List<Student>students = new ArrayList<>();

        students.add(new Student(1,"Ram",80.30,25));
        students.add(new Student(2,"Sham",81.30,24));
        students.add(new Student(3,"Calm",90.30,23));
        students.add(new Student(4,"Encore",99.30,22));

//        Collections.sort(students,new StudentScoreComparator());

//        ()->{};
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if(o1.score==o2.score){
                    return o1.id.compareTo(o2.id);
                }
                return o2.score.compareTo(o1.score);
            }
        });

//        HashMap
        for(Student student:students){
            System.out.println(student.id + " " + student.name + " " + student.score + " " + student.age);
        }

//        To create the natural ordering of a class we need to implement comparable.
//        for specific sorting we implement comparator(user defined sorting)
















    }
}
