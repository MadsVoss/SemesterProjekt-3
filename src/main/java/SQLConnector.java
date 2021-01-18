import java.sql.*;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.StringTokenizer;


public class SQLConnector {
    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                //connection = DriverManager.getConnection("jdbc:mysql://db.diplomportal.dk/s190608?" + "user=s190608&password=5USibIiZSIh7RR85vBFgH");
                connection = DriverManager.getConnection("jdbc:mariadb://192.168.239.21:3306/myuser", "madsboi", "pog");
                //connection = DriverManager.getConnection("jdbc:mariadb://[2001:878:200:4102:207:e9ff:fe62:b76]:3306/logins" + "user=madsboi&password=pog");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
