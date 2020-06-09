package Bank.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionToDB {
        public static Connection connection = null;
        private static final Logger logger = Logger.getLogger(ConnectionToDB.class.getName());
        private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
        private static final String DB_CONNECTION = "jdbc:mysql://localhost/ASBank?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        private static final String DB_USER = "root";
        private static final String DB_PASSWORD = "";

        private ConnectionToDB(){

        }

        public static Connection getConnToDB() throws SQLException {
                try {
                        Class.forName(DB_DRIVER);
                } catch (Exception e) {
                        e.printStackTrace();
                        logger.log(Level.SEVERE,e.getMessage());
                }

                try {
                        connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
                        return connection;
                } catch (SQLException exception) {
                        logger.log(Level.SEVERE, exception.getMessage());
                }
                return connection;
        }

}
