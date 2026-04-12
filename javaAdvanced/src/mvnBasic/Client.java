package mvnBasic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Client {

//    Relational Dbs -> {mysql,postgres,h2,oracle etc..}

    public static void main(String[] args) throws SQLException {


//        jdbc:- java database connectivity protocol
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dummyJbdl","root","oooooooo");
        Statement statement = connection.createStatement();
        statement.execute("create table dummy( id int, count int)");




    }
}
