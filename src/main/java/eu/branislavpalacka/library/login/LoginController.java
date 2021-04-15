package eu.branislavpalacka.library.login;

import eu.branislavpalacka.library.domain.Employee;
import eu.branislavpalacka.library.domain.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public String postBody(@RequestBody Login login){
        String result = loginService.controlForbiddenChar(login);

        if (result.equals("no forbidden characters")) {

            if (loginService.controlEmail(login.getEmail()).equals("email parameters are ok")) {

                User user = loginService.findUserByEmail(login);
                Employee employee = loginService.findEmployeeByEmail(login);

                if (user != null) {
                    return user.getName() + " " + user.getSurname();
                }else if (employee != null) {
                    return employee.getName() + " " + employee.getSurname();
                }else return "wrong password";
// TODO change wrong password to more variants
            } else return loginService.controlEmail(login.getEmail());

        }return result;

    }

}
