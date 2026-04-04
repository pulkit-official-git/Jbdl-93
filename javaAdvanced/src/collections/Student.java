package collections;

public class Student implements Comparable<Student>{

    Integer id;
    String name;
    Double score;
    Integer age;

    public Student(Integer id, String name, Double score, Integer age) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.age = age;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int compareTo(Student o) {
        return this.name.compareTo(o.name);
    }
}
