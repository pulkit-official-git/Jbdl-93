package com.example.demojbdl93bms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Repository
public class DatabaseRepository {
    String url;
    String username;
    String password;

    private Connection connection;

    public DatabaseRepository(@Value("${db.url}") String url,
                          @Value("${db.username}") String username,
                          @Value("${db.password}") String password) throws SQLException {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Connection getConnection() throws SQLException {
        if (this.connection == null) {
            this.connection = DriverManager.getConnection(this.url, this.username, this.password);
        }
        return this.connection;
    }
}
