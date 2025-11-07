package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=text_editor;encrypt=false;";
    private static final String USER = "Cucumber"; // або твій логін
    private static final String PASSWORD = "1657udte";

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }

    // Закриття з’єднання
    public static void closeConnection() {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                    System.out.println("🔒 Database connection closed.");
                }
            } catch (SQLException e) {
                System.out.println("⚠️ Error while closing database connection!");
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.println("✅ Connected successfully to database: " + conn.getCatalog());
        } catch (SQLException e) {
            System.out.println("❌ Database connection failed!");
            e.printStackTrace();
        }
    }
}