package chaining;

public class D extends C{
//    public void fred(){
//        System.out.println("inside fred");
//    }

    D(){
        super("ram");//super should always be first line of constructor
        System.out.println("inside D");
    }

}
