package polymorphism;

public class Student {

    int rollNo;

    String name;

//    Method Signature(function name + parameter)

//    method overloading(return type has to do nothing with method overloading)

    public void hello(){
        System.out.println("Hello student");
    }

    private int hello(String name){
        System.out.println("Hello");
        return 0;
    }

//    private void hello(String name){
//        System.out.println("hello "+name);
//    }

    public void hello(String name,int rollNo){
        System.out.println("hello "+name);
    }

    public void hello(String name,int rollNo,int legs){
        System.out.println("hello "+name);
    }





//    uts not even a method overloading
    public int HelloName(){
        return 0;
//        System.out.println("Hello , name is "+ name);
    }




//    its not even a thing in java
//    public int Hello(String name){
//        return 1;
//        System.out.println("Hello , name is "+ name);
//    }
}
