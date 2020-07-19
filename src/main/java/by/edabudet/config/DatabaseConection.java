package by.edabudet.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static by.edabudet.config.DatabaseConfig.*;

public class DatabaseConection{

    public Connection getDbConnection() throws SQLException {
        String connectionString = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME + "?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";
        return DriverManager.getConnection(connectionString, DB_USER, DB_PASS);
    }
}

