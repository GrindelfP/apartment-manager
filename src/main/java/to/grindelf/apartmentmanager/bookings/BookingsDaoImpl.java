package to.grindelf.apartmentmanager.bookings;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import to.grindelf.apartmentmanager.domain.Booking;
import to.grindelf.apartmentmanager.exceptions.NoSuchUserException;
import to.grindelf.apartmentmanager.exceptions.UserAlreadyExistsException;
import to.grindelf.apartmentmanager.utils.DataOperator;
import to.grindelf.apartmentmanager.utils.database.DatabaseDaoUtils;
import to.grindelf.apartmentmanager.utils.database.SQLOperator;
import to.grindelf.apartmentmanager.utils.json.JsonOperator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static to.grindelf.apartmentmanager.utils.ConstantValues.*;

@Component
public class BookingsDaoImpl implements BookingsDao<Booking> {

    private final DataOperator<Booking, Integer> operator;

    public BookingsDaoImpl() {
        this.operator = new SQLOperator<>();
    }

    public BookingsDaoImpl(DataOperator<Booking, Integer> operator) {
        this.operator = operator;
    }

    @Override
    public Booking getBookingById(int id) throws NoSuchUserException {

        Booking resultUser = null;

        if (this.operator instanceof SQLOperator<Booking, Integer>) {
            resultUser = sqlGetBookingByName(id);
        } else if (this.operator instanceof JsonOperator<Booking, Integer>) {
            resultUser = jsonGetBookingByName(id);
        }

        return resultUser;
    }

    private Booking sqlGetBookingByName(int id) throws NoSuchUserException {
        try {
            return this.operator.getByKey(
                    id,
                    BOOKINGS_COLUMN_KEY_COLUMN_NAME,
                    USER_DB_FILE_PATH,
                    DatabaseTableNames.USERS_TABLE,
                    new DatabaseDaoUtils().bookingMapper
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Booking jsonGetBookingByName(int id) throws NoSuchUserException {
        return null;
    }

    @Override
    public List<Booking> getAll() {

        List<Booking> users = new ArrayList<>();

        if (this.operator instanceof SQLOperator<Booking, Integer>) {
            users = sqlGetAll();
        } else if (this.operator instanceof JsonOperator<Booking, Integer>) {
            users = jsonGetAll();
        }

        return users;
    }

    private @NotNull List<Booking> sqlGetAll() {
        try {
            return this.operator.getAll(
                    USER_DB_FILE_PATH,
                    new DatabaseDaoUtils().bookingMapper,
                    DatabaseTableNames.BOOKINGS_TABLE
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Booking> jsonGetAll() {
        return null;
    }

    @Override
    public void save(@NotNull Booking user) throws UserAlreadyExistsException {

        if (this.operator instanceof SQLOperator<Booking, Integer>) {
            sqlSave(user);
        } else if (this.operator instanceof JsonOperator<Booking, Integer>) {
            jsonSave(user);
        }
    }

    private void jsonSave(@NotNull Booking user) throws UserAlreadyExistsException {
    }

    private void sqlSave(@NotNull Booking user) throws UserAlreadyExistsException {
        try {
            this.operator.post(
                    user,
                    USER_DB_FILE_PATH,
                    DatabaseTableNames.BOOKINGS_TABLE,
                    BOOKINGS_COLUMN_KEY_COLUMN_NAME
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(@NotNull Booking user) throws NoSuchUserException {

        if (this.operator instanceof SQLOperator<Booking, Integer>) {
            sqlUpdate(user);
        } else if (this.operator instanceof JsonOperator<Booking, Integer>) {
            jsonUpdate(user);
        }
    }

    private void sqlUpdate(@NotNull Booking booking) throws NoSuchUserException {
        try {
            this.operator.update(
                    booking.getId(),
                    BOOKINGS_COLUMN_KEY_COLUMN_NAME,
                    booking,
                    USER_DB_FILE_PATH,
                    DatabaseTableNames.BOOKINGS_TABLE
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void jsonUpdate(@NotNull Booking user) throws NoSuchUserException {

    }

    @Override
    public void delete(int id) throws NoSuchUserException {

        if (this.operator instanceof SQLOperator<Booking, Integer>) {
            sqlDelete(id);
        } else if (this.operator instanceof JsonOperator<Booking, Integer>) {
            jsonDelete(id);
        }
    }

    private void jsonDelete(int id) throws NoSuchUserException {

    }

    private void sqlDelete(int id) throws NoSuchUserException {
        try {
            this.operator.delete(
                    id,
                    BOOKINGS_COLUMN_KEY_COLUMN_NAME,
                    USER_DB_FILE_PATH,
                    DatabaseTableNames.BOOKINGS_TABLE
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean checkIfUserExists(@NotNull String userName, @NotNull List<Booking> userList) {
        return false;
    }
}
