package multithreadedSingleton;

public class RedisEager {

    String url;
    String username;
    String password;
    int port;

    private static final RedisEager instance = new RedisEager("","","",6379);

    private RedisEager(String url, String username, String password, int port) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.port = port;
    }

    public static RedisEager getInstance(){
        return instance;
    }
}
