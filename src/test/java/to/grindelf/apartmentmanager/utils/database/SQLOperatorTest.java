package to.grindelf.apartmentmanager.utils.database;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import to.grindelf.apartmentmanager.domain.User;
import to.grindelf.apartmentmanager.domain.UserStatus;
import to.grindelf.apartmentmanager.exceptions.NoSuchUserException;
import to.grindelf.apartmentmanager.exceptions.UserAlreadyExistsException;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class SQLOperatorTest {

    private final String PATH = "src/test/resources/test-db.db";

    @Test
    void initializeDatabase() {

        String testBaseUrl = "jdbc:sqlite:" + PATH;

        File dbFile = new File("users.db");
        if (!dbFile.exists()) {
            try {
                dbFile.createNewFile();
                System.out.println("DB file was just created");
            } catch (Exception e) {
                System.out.println("Error creating DB file: " + e.getMessage());
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
            System.out.println("Error DB initialisation: " + e.getMessage());
        }
    }

    @Test
    @Order(1)
    void givenUser_thenPostedToDB() throws SQLException, UserAlreadyExistsException {
        SQLOperator<User, String> operator = new SQLOperator<>();

        User user = new User("dad", "4sadhk3", UserStatus.JUST_USER);

        operator.post(
                user,
                PATH,
                DatabaseTableNames.USERS_TABLE
        );
    }

    @Test
    @Order(2)
    void givenExistentUser_whenPostedToDB_thenUserAlreadyExistsExceptionThrown() throws SQLException {
        SQLOperator<User, String> operator = new SQLOperator<>();

        User user = new User("Ira", "srusrtjhsrtjhs", UserStatus.JUST_USER);

        assertThrows(UserAlreadyExistsException.class, () -> operator.post(
                user,
                PATH,
                DatabaseTableNames.USERS_TABLE
        ));
    }

    @Test
    @Order(3)
    void givenUpdateUserData_thenUserUpdated() throws SQLException, NoSuchUserException {
        SQLOperator<User, String> operator = new SQLOperator<>();

        User userUpdate = new User("Ira", "qwerty", UserStatus.ADMIN);

        operator.update(
                "Ira",
                "name",
                userUpdate,
                PATH,
                DatabaseTableNames.USERS_TABLE
        );
    }

    @Test
    @Order(4)
    void givenUpdateNonExistentUserData_whenUserUpdated_thenNoSuchUserExceptionIsThrown() throws SQLException {
        SQLOperator<User, String> operator = new SQLOperator<>();

        User userUpdate = new User("ksj", "-", UserStatus.JUST_USER);


        assertThrows(NoSuchUserException.class, () -> operator.update(
                "no-such-user",
                "name",
                userUpdate,
                PATH,
                DatabaseTableNames.USERS_TABLE
        ));
    }

    @Test
    @Order(5)
    void userDeleted() throws SQLException, NoSuchUserException { // works but not tells if no such user
        SQLOperator<User, String> operator = new SQLOperator<>();

        operator.delete(
                "Ira",
                "name",
                PATH,
                DatabaseTableNames.USERS_TABLE
        );
    }

    @Test
    @Order(6)
    void nonExistingUserNotDeleted() throws SQLException, NoSuchUserException { // works but not tells if no such user
        SQLOperator<User, String> operator = new SQLOperator<>();


        assertThrows(NoSuchUserException.class, () -> operator.delete(
                "no-such-user",
                "name",
                PATH,
                DatabaseTableNames.USERS_TABLE
        ));
    }

    @Test
    @Order(7)
    void readAllFromDB() throws SQLException { // works
        SQLOperator<User, String> operator = new SQLOperator<>();

        List<User> users = operator.getAll(
                PATH,
                new DatabaseDaoUtils().userMapper,
                "users"
        );

        System.out.println(users.size());

        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    @Order(8)
    void givenKey_thenGetUserByKey() throws SQLException, NoSuchUserException {
        SQLOperator<User, String> operator = new SQLOperator<>();

        User user = operator.getByKey(
                "dad",
                "name",
                PATH,
                DatabaseTableNames.USERS_TABLE,
                new DatabaseDaoUtils().userMapper
        );

        System.out.println(user);
    }

    @Test
    @Order(9)
    void givenWrongKey_whenGetByKey_thenCatchSQLException() {
        SQLOperator<User, String> operator = new SQLOperator<>();

        assertThrows(NoSuchUserException.class, () -> operator.getByKey(
                "no-such-user",
                "name",
                PATH,
                DatabaseTableNames.USERS_TABLE,
                new DatabaseDaoUtils().userMapper
        ));
    }
}
