package polymorphism;

public class Client {
    public static void main(String[] args) {

        Student s1 = new Student();
        s1.hello();

        Student student = new Topper();
        student.hello();

    }
}
