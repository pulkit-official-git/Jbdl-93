package stations;

public class Client {

    public static void main(String[] args) {
//        int[] arr = {900, 940, 950, 1100, 1500, 1800};
//        int[] dep = {910, 1200, 1120, 1130, 1900, 2000};
//        int []arr = new int[]{900, 940, 950, 1100};
//        int []dep = new int[]{910, 1200, 1120, 1130};

//        int[] arr = {900, 905, 915, 920, 925};
//        int[] dep = {1000, 1005, 1030, 1040, 1050};
        int[] arr = {900, 940, 950, 1100, 1500, 1800};
        int[] dep = {910, 1200, 1120, 1130, 1900, 2000};
// Expected output: 3

        Station s = new Station();
        System.out.println(s.minPlatform(arr, dep));

    }
}
