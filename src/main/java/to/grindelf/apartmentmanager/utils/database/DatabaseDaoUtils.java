package to.grindelf.apartmentmanager.utils.database;

import to.grindelf.apartmentmanager.domain.Booking;
import to.grindelf.apartmentmanager.domain.User;

/**
 * Utility class for DatabaseDao.
 */
public class DatabaseDaoUtils {

    /**
     * Mapper for User object.
     * It maps the result set to a User object.
     */
    public final RowMapper<User> userMapper = rs -> new User(
            rs.getString("name"),
            rs.getString("password"),
            rs.getString("status")
    );

    /**
     * Mapper for Booking object.
     * It maps the result set to a User object.
     */
    public final RowMapper<Booking> bookingMapper = rs -> new Booking(
            rs.getInt("id"),
            rs.getInt("apartmentId"),
            rs.getString("bookerName"),
            rs.getString("dateFrom"),
            rs.getString("dateTo")
    );
}
