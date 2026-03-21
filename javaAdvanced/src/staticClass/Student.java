package staticClass;

public class Student {

    final static String  univ = "gfg";

    int rollNo;

    String name;

    double score;

    static void printUniv() {
        System.out.println();
    }

    public Student() {
    }

    public Student(int rollNo, String name, double score) {
        this.rollNo = rollNo;
        this.name = name;
        this.score = score;
    }
}
