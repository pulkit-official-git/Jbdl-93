package com.example.bookmanagement93;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Repository
public class DatabaseRepository {

    private Connection connection;
    String username;
    String password;
    String url;

    public DatabaseRepository(@Value("${db.url}") String url,
                              @Value("${db.username}") String username,
                              @Value("${db.password}") String password) throws SQLException {
        this.username = username;
        this.password = password;
        this.url = url;
    }

    public Connection getConnection() throws SQLException {
        if(connection==null){
            connection = DriverManager.getConnection(url, username, password);
        }
        return connection;
    }
}
