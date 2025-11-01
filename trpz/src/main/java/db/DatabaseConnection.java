package db;

import java.sql.Connection;

public class DatabaseConnection {
    public Connection connection;
    public Connection getConnection() { return connection; }
    public void setConnection(Connection connection) { this.connection = connection; }
}
