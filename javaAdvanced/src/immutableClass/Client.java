package immutableClass;

public class Client {

    public static void main(String[] args) {

        Ranking ranking = new Ranking(1,"mumbai");

        Player player1 = new Player("fred",20,ranking);

        System.out.println(player1.getRanking().getId());

        ranking.setId(2);

//        Ranking dummyRank = player1.getRanking();
//        dummyRank.setId(2);

        System.out.println(player1.getRanking().getId());
    }
}
