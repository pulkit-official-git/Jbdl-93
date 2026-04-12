package immutableClass;

public class Ranking {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Ranking(Integer id, String city) {
        this.id = id;
        this.city = city;
    }

    private String city;


}
