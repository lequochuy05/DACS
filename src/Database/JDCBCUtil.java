package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDCBCUtil {
    public static Connection getConnection() throws SQLException {
        Connection c = null;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

            String url = "jdbc:mysql://127.0.0.1:3306/qlcb";
            String userName = "root";
            String password = "";

            c = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            throw e; // Rethrow the exception to let the caller handle it
        }
        return c;
    }

    public static void closeConnection(Connection c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
