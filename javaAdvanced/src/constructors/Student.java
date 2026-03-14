package constructors;

public class Student {

    int rollNo;
    String name="raman";
    double score;

    Student(){
//        name="sham";
    }
    Student(int rollNo,String name){
        this.rollNo=rollNo;
        this.name=name;
    }
    Student(int score,String name,int rollNo){
        this.rollNo=rollNo;
        this.name=name;
        this.score=score;
    }

    Student(Student s){
        this.rollNo=s.rollNo;
        this.name=s.name;
        this.score=s.score;
    }

//    @Override
//    public String toString() {
//        return "Student{" +
//                "rollNo=" + rollNo +
//                ", name='" + name + '\'' +
//                ", score=" + score +
//                '}';
//    }
}
