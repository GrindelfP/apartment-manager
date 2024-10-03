package to.grindelf.apartmentmanager.auth;

import org.jetbrains.annotations.NotNull;
import to.grindelf.apartmentmanager.exceptions.NoSuchUserException;
import to.grindelf.apartmentmanager.exceptions.UserAlreadyExistsException;

import java.util.List;

public interface UserDao<T> {

    T getUser(@NotNull String userName) throws NoSuchUserException;
    List<T> getAll();
    void save(@NotNull T user) throws UserAlreadyExistsException;
    void update(@NotNull T user) throws NoSuchUserException;
    void delete(@NotNull String userName) throws NoSuchUserException;
}
