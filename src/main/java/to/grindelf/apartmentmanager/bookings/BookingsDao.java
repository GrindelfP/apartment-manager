package to.grindelf.apartmentmanager.bookings;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import to.grindelf.apartmentmanager.exceptions.NoSuchUserException;
import to.grindelf.apartmentmanager.exceptions.UserAlreadyExistsException;

import java.sql.SQLException;
import java.util.List;

@Component
public interface BookingsDao<T> {

    T getBookingById(int id) throws NoSuchUserException;
    List<T> getAll() throws SQLException;
    void save(@NotNull T user) throws UserAlreadyExistsException;
    void update(@NotNull T user) throws NoSuchUserException;
    void delete(int id) throws NoSuchUserException;
}
