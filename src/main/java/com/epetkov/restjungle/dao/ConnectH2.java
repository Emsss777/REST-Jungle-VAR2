package com.epetkov.restjungle.dao;

import org.springframework.stereotype.Component;
import java.sql.*;

@Component
public class ConnectH2 {

    public Connection getConnection() {

        String jdbcURL = "jdbc:h2:mem:testdb";
        String jdbcUser = "sa";
        String jdbcPass = "password";

        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPass);

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return connection;
    }
}
