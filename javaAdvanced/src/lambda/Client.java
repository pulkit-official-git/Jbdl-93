package lambda;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Client {

    public static void main(String[] args) {

        /*
        * Runnable
        * Callable
        * Comparator
        * Comparable
        *
        * */


        List<Student> students = new ArrayList<>();

        students.add(new Student(1,"Ram",80.30,25));
        students.add(new Student(2,"Sham",81.30,24));
        students.add(new Student(3,"Calm",90.30,23));
        students.add(new Student(4,"Encore",99.30,22));

//        way1
        Collections.sort(students, new StudentScoreComparator());
        for(Student student:students){
            System.out.println(student.id + " " + student.name + " " + student.score + " " + student.age);
        }

//        way2
        Collections.sort(students,(Student o1, Student o2)->{ return  o1.score.compareTo(o2.score);});

        //        way3
        Collections.sort(students,( o1,  o2)->{ return  o1.score.compareTo(o2.score);});


        //        way4
        Collections.sort(students,( o1,  o2)-> o1.score.compareTo(o2.score));

        //        way5
//        Collections.sort(students, o1-> o1.score*o1.score); this will also work if we have only one input parameter;

        Runnable runnable = () ->System.out.println("Hello World");
        Thread thread = new Thread(runnable);
        thread.start();


        Thread t2 = new Thread(()->{
            System.out.println("Hello World");
        });
        t2.start();


        Calculator additionCalculator = (a,b)->a+b;

        System.out.println(additionCalculator.operate(5,6));

        Calculator subtract = (a,b)->{

            if(a<b){
                return b-a;
            }
            else return a-b;
        };

        System.out.println(subtract.operate(5,4));


    }
}

/*
* POM
* Maven
* Rest
* MVC
* */
