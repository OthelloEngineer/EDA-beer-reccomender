package org.com.database.adapters;

import org.com.database.DBservice;
import org.com.events.CreateUser;
import org.postgresql.jdbc.*;
import org.postgresql.jdbc2.optional.ConnectionPool;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PostgrestQLAdapter implements DBservice {
    Connection connection;
    public PostgrestQLAdapter() {
        try {
            String url = "jdbc:postgresql://localhost:5432/";
            Properties properties = new Properties();
            properties.setProperty("user", "postgres");
            properties.setProperty("password", "postgres");
            this.connection = DriverManager.getConnection(url, properties);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(CreateUser createUser) {
        try (CallableStatement statement = connection.prepareCall("CALL insert_user(" +
                "cast(? as text),?,cast(? as text),cast(? as text),cast(? as text),cast(? as text),cast(? as text),cast(? as text))")){
            statement.setString(1, createUser.name);
            statement.setInt(2, createUser.age);
            statement.setString(3, createUser.email);
            statement.setString(4, createUser.phone);
            statement.setString(5, createUser.city);
            statement.setString(6, createUser.address);
            statement.setString(7, createUser.zip);
            statement.setString(8, createUser.country);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}