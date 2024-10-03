package to.grindelf.apartmentmanager.auth;

import org.jetbrains.annotations.NotNull;
import to.grindelf.apartmentmanager.domain.User;
import to.grindelf.apartmentmanager.exceptions.NoSuchUserException;
import to.grindelf.apartmentmanager.exceptions.UserAlreadyExistsException;
import to.grindelf.apartmentmanager.utils.JsonOperator;

import java.util.List;
import java.util.Objects;

public class UserDaoJson implements UserDao<User> {

    private final List<User> userList;
    private final String USER_FILE_PATH = "resources/users.json";
    private final JsonOperator<List<User>> operator;

    public UserDaoJson() {
        this.operator = new JsonOperator<>(USER_FILE_PATH);
        this.userList = this.operator.readFile();
    }

    @Override
    public User getUser(@NotNull String userName) throws NoSuchUserException {
        User resultUser = null;
        for (User user : userList) {
            if (Objects.equals(user.getName(), userName)) {
                resultUser = user;
                break;
            }
        }
        if (!checkIfUserExists(userName)) {
            throw new NoSuchUserException();
        } else return resultUser;
    }

    @Override
    public List<User> getAll() {
        return userList;
    }

    @Override
    public void save(@NotNull User user) throws UserAlreadyExistsException {
        if (checkIfUserExists(user.getName())) {
            throw new UserAlreadyExistsException();
        } else {
            userList.add(user);
        }
    }

    @Override
    public void update(@NotNull User user) throws NoSuchUserException {
        if (!checkIfUserExists(user.getName())) {
            throw new NoSuchUserException();
        } else {
            for (int i = 0; i < userList.size(); i++) {
                if (Objects.equals(userList.get(i).getName(), user.getName())) {
                    userList.set(i, user);
                    break;
                }
            }
        }
    }

    @Override
    public void delete(@NotNull String userName) throws NoSuchUserException {
        if (!checkIfUserExists(userName)) {
            throw new NoSuchUserException();
        } else {
            for (int i = 0; i < userList.size(); i++) {
                if (Objects.equals(userList.get(i).getName(), userName)) {
                    userList.remove(i);
                    break;
                }
            }
        }
    }

    private boolean checkIfUserExists(String userName) {
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
