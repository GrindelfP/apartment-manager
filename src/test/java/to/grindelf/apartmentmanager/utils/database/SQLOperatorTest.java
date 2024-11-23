package to.grindelf.apartmentmanager.utils.database;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import to.grindelf.apartmentmanager.domain.User;
import to.grindelf.apartmentmanager.domain.UserStatus;

import java.io.Console;
import java.io.File;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SQLOperatorTest {

    private final String PATH = "src/test/resources/test-db.db";

    @Test
    void init() {

        String testBaseUrl = "jdbc:sqlite:" + PATH;

        File dbFile = new File("users.db");
        if (!dbFile.exists()) {
            try {
                // Создаем новый файл базы данных
                dbFile.createNewFile();
                System.out.println("Файл базы данных создан.");
            } catch (Exception e) {
                System.out.println("Ошибка создания файла базы данных: " + e.getMessage());
            }
        }

        String sql = "CREATE TABLE IF NOT EXISTS " + DatabaseTableNames.USERS_TABLE + " (" +
                "name TEXT PRIMARY KEY, " +
                "password TEXT, " +
                "status TEXT)";

        try (Connection conn = DriverManager.getConnection(testBaseUrl);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Ошибка инициализации базы данных: " + e.getMessage());
        }
    }

    @Test
    @Order(1)
    void insert() throws SQLException {
        SQLOperator<User, String> operator = new SQLOperator<>();

        User user = new User("Ira", "123", UserStatus.ADMIN);

        operator.post(
                user,
                PATH,
                DatabaseTableNames.USERS_TABLE
        );
    }

    @Test
    @Order(2)
    void update() throws SQLException {
        SQLOperator<User, String> operator = new SQLOperator<>();

        User userUpdate = new User("Ira My Ira", "123", UserStatus.JUST_USER);
        Class<?> clazz = userUpdate.getClass();
        Field[] fields = clazz.getFields();

        operator.update(
                "Ira",
                "name",
                userUpdate,
                PATH,
                DatabaseTableNames.USERS_TABLE
        );
    }

    @Test
    @Order(3)
    void delete() throws SQLException {
        SQLOperator<User, String> operator = new SQLOperator<>();

        operator.delete(
                "Ira",
                "name",
                PATH,
                DatabaseTableNames.USERS_TABLE
        );
    }

    @Test
    @Order(4)
    void readAll() throws SQLException {
        SQLOperator<User, String> operator = new SQLOperator<>();

        List<User> users = operator.getAll(
                PATH,
                new DatabaseDaoUtils().userMapper,
                "users"
        );

        for (User user : users) {
            System.out.println(user);
        }
    }
}