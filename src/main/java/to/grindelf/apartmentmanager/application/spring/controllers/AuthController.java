//package to.grindelf.apartmentmanager.application.spring.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import to.grindelf.apartmentmanager.application.spring.services.AuthService;
//import to.grindelf.apartmentmanager.auth.UserDaoImpl;
//import to.grindelf.apartmentmanager.domain.User;
//import to.grindelf.apartmentmanager.exceptions.NoSuchUserException;
//import to.grindelf.apartmentmanager.exceptions.UserAlreadyExistsException;
//import to.grindelf.apartmentmanager.utils.database.SQLOperator;
//
//@Controller
//public class AuthController {
//
//    private final UserDaoImpl dao = new UserDaoImpl(new SQLOperator<>());
//
//    @GetMapping("/login")
//    public String showLoginPage() {
//        return "login"; // имя шаблона login.html
//    }
//
//    @PostMapping("/login")
//    public String login(
//            @RequestParam String name,
//            @RequestParam String password,
//            Model model
//    ) {
//        User loginUser = new User(name, password);
//        try {
//            User realUser = dao.getUserByName(name);
//            if (realUser.equals(loginUser) && realUser.isAdmin()) {
//                model.addAttribute("message", "Welcome, Admin!");
//                return "welcome"; // шаблон для админа
//            } else if (realUser.equals(loginUser) && realUser.isJustUser()) {
//                model.addAttribute("message", "Welcome, User!");
//                return "welcome"; // шаблон для обычного пользователя
//            }
//        } catch (NoSuchUserException e) {
//            model.addAttribute("error", "Incorrect login or password.");
//            return "login"; // если ошибка, возвращаемся на страницу логина
//        }
//        return "login"; // если ошибка, возвращаемся на страницу логина
//    }
//}
