package eu.branislavpalacka.library.login;

import eu.branislavpalacka.library.domain.Employee;
import eu.branislavpalacka.library.domain.User;
import eu.branislavpalacka.library.services.api.UserService;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LoginServiceImpl implements LoginService{
    private final UserService userService;
    private final LoginRepository loginRepository;

    public LoginServiceImpl(UserService userService, LoginRepository loginRepository) {
        this.userService = userService;
        this.loginRepository = loginRepository;
    }

    @Override
    public String controlForbiddenChar(Login login) {
        String email = login.getEmail();
        String password = login.getPassword();

        String regex = "[^\\/*\\+!?;]";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcherEmail = pattern.matcher(email);
        Matcher matcherPassword = pattern.matcher(password);

        int emailMatcherLength = 0;
        while (matcherEmail.find()){
            emailMatcherLength++;
        }

        int passwordMatcherLength =0;
        while (matcherPassword.find()){
            passwordMatcherLength++;
        }

        if (emailMatcherLength != email.length()) {
            return "There are forbidden characters in email.";
        } else if (passwordMatcherLength != password.length()) {
            return "There are forbidden characters in password.";
        } else return null;
    }

    @Override
    public String controlEmail(String email) {
        String regex = "[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+";
        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

        String result = null;

        if (matcher.equals(null)){
           result = "Email contains wrong parameters.";
        }else {
            while (matcher.find()){
                result = matcher.group();
            }
        }
        return result;
    }


    @Override
    public User findUserByEmail(Login login) {
        User user = loginRepository.findUserByEmail(login.getEmail());
        if(user != null){
               if(user.getPassword().equals(login.getPassword())){
                    return user;
               }else {
                   System.out.println("wrong password");
                   return null;
               }
        }else {
            System.out.println("Users email not found.");
            return null;
        }
    }

    @Override
    public Employee findEmployeeByEmail(String email) {
        return loginRepository.findEmployeeByEmail(email);
    }


}
