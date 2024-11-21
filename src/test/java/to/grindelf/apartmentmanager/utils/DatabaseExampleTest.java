package to.grindelf.apartmentmanager.utils;

import org.junit.jupiter.api.Test;
import to.grindelf.apartmentmanager.domain.User;
import to.grindelf.apartmentmanager.domain.UserStatus;
import to.grindelf.apartmentmanager.utils.database.DatabaseExample;

import java.util.Arrays;
import java.util.List;

@Deprecated
class DatabaseExampleTest {

    private final List<User> testUsers = Arrays.asList(
            new User(
                    "jon_doe",
                    "123",
                    UserStatus.ADMIN
            ),
            new User(
                    "jane_doe"
            ),
            new User(
                    "jack_sparrow",
                    "123"

            ));

    @Test
    void createTest() {

        DatabaseExample.createDatabase();

    }


    @Test
    void insertTest() {

        DatabaseExample.insertUsers(testUsers);
    }

    @Test
    void getUserByNameTest() {

        User user = DatabaseExample.getUserByName("jack_sparrow");

        System.out.println(user);

    }

    @Test
    void getUsersTest() {
        List<User> userList = DatabaseExample.getAllUsers();

        for (User user : userList) {
            System.out.println(user);
        }

    }

}