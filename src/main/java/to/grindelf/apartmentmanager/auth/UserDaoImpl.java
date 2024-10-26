package to.grindelf.apartmentmanager.auth;

import org.jetbrains.annotations.NotNull;
import to.grindelf.apartmentmanager.domain.User;
import to.grindelf.apartmentmanager.exceptions.NoSuchUserException;
import to.grindelf.apartmentmanager.exceptions.UserAlreadyExistsException;
import to.grindelf.apartmentmanager.utils.DataOperator;

import java.util.List;
import java.util.Objects;

public class UserDaoImpl implements UserDao<User> {

    private final String USER_FILE_PATH = "resources/users.json";
    private final DataOperator<List<User>> operator;

    public UserDaoImpl(DataOperator<List<User>> operator) {
        this.operator = operator;
    }

    @Override
    public User getUserByName(@NotNull String userName) throws NoSuchUserException {

        List<User> userList = this.operator.readFile(USER_FILE_PATH);

        User resultUser = null;
        for (User user : userList) {
            if (Objects.equals(user.getName(), userName)) {
                resultUser = user;
                break;
            }
        }
        if (!checkIfUserExists(userName, userList)) {
            throw new NoSuchUserException();
        } else
            return resultUser;

    }

    @Override
    public List<User> getAll() {
        return this.operator.readFile(USER_FILE_PATH);
    }

    @Override
    public void save(@NotNull User user) throws UserAlreadyExistsException {

        List<User> userList = this.operator.readFile(USER_FILE_PATH);

        if (checkIfUserExists(user.getName(), userList)) {
            throw new UserAlreadyExistsException();
        } else {
            userList.add(user);
        }

        this.operator.writeToFile(USER_FILE_PATH, userList);
    }

    @Override
    public void update(@NotNull User user) throws NoSuchUserException {

        List<User> userList = this.operator.readFile(USER_FILE_PATH);

        if (!checkIfUserExists(user.getName(), userList)) {
            throw new NoSuchUserException();
        } else {
            for (int i = 0; i < userList.size(); i++) {
                if (Objects.equals(userList.get(i).getName(), user.getName())) {
                    userList.set(i, user);
                    break;
                }
            }
        }

        this.operator.writeToFile(USER_FILE_PATH, userList);
    }

    @Override
    public void delete(@NotNull String userName) throws NoSuchUserException {

        List<User> userList = this.operator.readFile(USER_FILE_PATH);

        if (!checkIfUserExists(userName, userList)) {
            throw new NoSuchUserException();
        } else {
            for (int i = 0; i < userList.size(); i++) {
                if (Objects.equals(userList.get(i).getName(), userName)) {
                    userList.remove(i);
                    break;
                }
            }
        }

        this.operator.writeToFile(USER_FILE_PATH, userList);
    }

    private boolean checkIfUserExists(@NotNull String userName, @NotNull List<User> userList) {
        boolean exists = false;
        for (User currentUser : userList) {
            if (Objects.equals(userName, currentUser.getName())) {
                exists = true;
                break;
            }
        }

        return exists;
    }
}
