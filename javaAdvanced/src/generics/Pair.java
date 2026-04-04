package generics;

public class Pair <A,B>{

      A  first;
      B  second;

      public void setA(A value){
          this.first=value;
      }

      public B getB(){
          return this.second;
      }

      public void doSomething(A a){
          System.out.println(a);
      }

//      Method level generics override Class level generic
      public <A> void doSomething2(A a){
        System.out.println(a);
      }

//      Method level generics
      public <Z> void doesSomething(Z z){
          System.out.println(z);
      }

      public static <A> void doing(A a){
          System.out.println(a);
      }
}


