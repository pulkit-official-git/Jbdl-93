package constructors;

public class Client {

    public static void main(String[] args) {
        Student student = new Student(10,"hell");
//        student.name = "ram";
//        student.rollNo = 12345;
        System.out.println(student);

        Student s2 = student;

        Student s3 = new Student();
        s3=student;

        Student s4 = new Student(student);
        System.out.println(s4);
//        s4.rollNo=student.rollNo;
//        s4.name=student.name;
//        s4.score=student.score;


//        without new keyword, no object is created



    }
}
