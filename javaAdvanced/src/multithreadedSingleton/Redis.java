package multithreadedSingleton;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Redis {

    String url;
    String username;
    String password;
    int port;

    public static Redis instance;

    private Redis(String url, String username, String password, int port) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.port = port;
    }


    public static Redis getInstance(){
        if(instance == null){
            synchronized (Redis.class){
                if(instance == null){
                    instance = new Redis("localhost","root","root",6379);
                }
            }
        }
        return instance;
    }


}
