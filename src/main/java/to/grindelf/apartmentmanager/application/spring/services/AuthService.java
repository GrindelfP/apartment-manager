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

    // Sign-up functionality
    public void signUp(String name, String password) throws UserAlreadyExistsException {
        User user = new User(name, password);
        userDao.save(user); // Save the user into the database
    }

    // Log-in functionality
    public User logIn(String name, String password) throws NoSuchUserException {
        User user = userDao.getUserByName(name);
        User loginUser = new User(name, password);
        if (loginUser.equals(user)) {
            return user; // Return the user if password matches
        } else {
            throw new NoSuchUserException();
        }
    }
}


