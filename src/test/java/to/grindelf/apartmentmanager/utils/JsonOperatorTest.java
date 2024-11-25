package to.grindelf.apartmentmanager.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import to.grindelf.apartmentmanager.domain.UserStatus;
import to.grindelf.apartmentmanager.domain.User;
import to.grindelf.apartmentmanager.exceptions.JSONException;
import to.grindelf.apartmentmanager.utils.json.JsonOperator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

class JsonOperatorTest {

    private final String PATH_FOR_ONE = "src/main/resources/users.json";
    private final String PATH_FOR_MANY = "src/main/resources/users2.json";
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
    private final User testUser = new User(
            "jon_doe",
            "123",
            UserStatus.ADMIN
    );
    private final String testUsersAsText = "[" +
            "{\"name\":\"jon_doe\",\"password\":\"123\",\"status\":\"ADMIN\"}," +
            "{\"name\":\"jane_doe\",\"password\":null,\"status\":\"JUST_USER\"}," +
            "{\"name\":\"jack_sparrow\",\"password\":\"123\",\"status\":\"JUST_USER\"}" +
            "]";
    private final String testUserAsText = "{\"name\":\"jon_doe\",\"password\":\"123\",\"status\":\"ADMIN\"}";

    @Test
    @Order(1)
    void givenOneUserData_whenWrittenToFile_thenTextInFileIsCorrect() {

        DataOperator<User, String> jsonOperator = new JsonOperator<>(new TypeReference<>() {
        });

        try {
            jsonOperator.writeToFile(PATH_FOR_ONE, testUser);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            String realUserAsText = Files.readString(Path.of(PATH_FOR_ONE));
            assert (Objects.equals(realUserAsText, testUserAsText));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(2)
    void givenUserDataInFile_whenRead_thenEqualToCorrectData() {
        DataOperator<User, String> jsonOperator = new JsonOperator<>(new TypeReference<>() {
        });

        User userFromFile = jsonOperator.readFile(PATH_FOR_ONE);

        assert (testUser.equals(userFromFile));
    }

    @Test
    @Order(3)
    void givenUsersData_whenWrittenToFile_thenTextInFileIsCorrect() {
        DataOperator<List<User>, String> jsonOperator = new JsonOperator<>(new TypeReference<>() {
        });

        try {
            jsonOperator.writeToFile(PATH_FOR_MANY, testUsers);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            String realUserAsText = Files.readString(Path.of(PATH_FOR_MANY));
            assert (Objects.equals(realUserAsText, testUsersAsText));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(4)
    void givenUsersDataInFile_whenRead_thenEqualToCorrectData() {
        DataOperator<List<User>, String> jsonOperator = new JsonOperator<>(new TypeReference<>() {
        });

        List<User> userFromFile = jsonOperator.readFile(PATH_FOR_MANY);

        for (int i = 0; i < testUsers.size(); i++) {
            assert (testUsers.get(i).equals(userFromFile.get(i)));
        }
    }
}
