package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/kata";
        return DriverManager.getConnection(
                url, "root", "1972");
    }
    // реализуйте настройку соеденения с БД
}
