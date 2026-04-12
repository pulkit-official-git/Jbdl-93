package hashmapObject;

import java.util.Objects;

public class Student {

    String name;
    Integer age;

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name,age);
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj){
            return true;
        }
        if(obj == null){
            return false;
        }

        if(getClass() != obj.getClass()){
            return false;
        }

        Student other = (Student) obj;

        return age == other.age && Objects.equals(name,other.name);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
