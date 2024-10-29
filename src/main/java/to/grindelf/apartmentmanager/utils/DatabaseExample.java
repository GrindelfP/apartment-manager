package to.grindelf.apartmentmanager.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import to.grindelf.apartmentmanager.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class DatabaseExample {
    private static final String DB_URL = "jdbc:sqlite:src/main/resources/users.db";

    public static void createDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            if (conn != null) {
                String createTableSQL = """
                            CREATE TABLE IF NOT EXISTS users (
                                id INTEGER PRIMARY KEY AUTOINCREMENT,
                                name TEXT NOT NULL,
                                password TEXT NOT NULL,
                                status TEXT NOT NULL
                            );
                        """;
                conn.createStatement().execute(createTableSQL);
                System.out.println("Таблица создана или уже существует.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertUsers(@NotNull List<User> users) {
        String insertSQL = "INSERT INTO users(name, password, status) VALUES(?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            for (User user : users) {
                pstmt.setString(1, user.getName());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getStatus().toString());
                pstmt.executeUpdate();
            }
            System.out.println("Пользователи успешно добавлены.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static @Nullable User getUserByName(String name) {
        String query = "SELECT name, password, status FROM users WHERE name = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getString("name"), rs.getString("password"), rs.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static @NotNull List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT name, password, status FROM users";
        try (Connection conn = DriverManager.getConnection(DB_URL); PreparedStatement pstmt = conn.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                users.add(new User(rs.getString("name"), rs.getString("password"), rs.getString("status")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
