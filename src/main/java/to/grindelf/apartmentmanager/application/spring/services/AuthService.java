package to.grindelf.apartmentmanager.application.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import to.grindelf.apartmentmanager.auth.UserDao;
import to.grindelf.apartmentmanager.domain.User;
import to.grindelf.apartmentmanager.exceptions.NoSuchUserException;
import to.grindelf.apartmentmanager.exceptions.UserAlreadyExistsException;

@Service
public class AuthService {

    @Autowired
    private UserDao<User> userDao;

    // Method for user registration
    public void signUp(User user) throws UserAlreadyExistsException {
        userDao.save(user);  // Use BookingsDao's save method to register the user
    }

    // Method for user login
    public void login(User user) throws NoSuchUserException {
        User existingUser = userDao.getUserByName(user.getName());
        // Check if the password matches
        if (existingUser == null || !existingUser.getPassword().equals(user.getPassword())) {
            throw new NoSuchUserException(); // If not, throw exception
        }
    }
}


