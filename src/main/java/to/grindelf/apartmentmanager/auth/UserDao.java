package to.grindelf.apartmentmanager.auth;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import to.grindelf.apartmentmanager.exceptions.NoSuchUserException;
import to.grindelf.apartmentmanager.exceptions.UserAlreadyExistsException;

import java.sql.SQLException;
import java.util.List;

@Component
public interface UserDao<T> {

    T getUserByName(@NotNull String userName) throws NoSuchUserException;
    List<T> getAll() throws SQLException;
    void save(@NotNull T user) throws UserAlreadyExistsException;
    void update(@NotNull T user) throws NoSuchUserException;
    void delete(@NotNull String userName) throws NoSuchUserException;
}
