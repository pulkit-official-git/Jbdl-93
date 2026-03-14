package inheritance;

public class Animal {

    protected String name;
    protected String color;
    protected int age;

    public Animal( String color,String name, int age) {
        this.name = name;
        this.color = color;
        this.age = age;
    }

    public Animal() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void eat(){
        System.out.println("animal eating");
    }

    public void sound(){
        System.out.println("animal sound");
    }

}
