package immutableClass;

public final class Player {

    private final String name;
    private final Integer age;
    private final Ranking ranking;

    public Player(String name, Integer age, Ranking ranking) {
        this.name = name;
        this.age = age;
        Ranking dummy = new Ranking(ranking.getId(),ranking.getCity());
        this.ranking = dummy;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Ranking getRanking() {
        Ranking dummyRank = new Ranking(ranking.getId(), ranking.getCity());
        return dummyRank;
    }
}
