package eu.branislavpalacka.library.login;

import eu.branislavpalacka.library.domain.Employee;
import eu.branislavpalacka.library.domain.User;
import eu.branislavpalacka.library.services.api.UserService;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LoginServiceImpl implements LoginService{

    private final LoginRepository loginRepository;

    public LoginServiceImpl(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public String controlForbiddenChar(Login login) {
        String email = login.getEmail();
        String password = login.getPassword();

        String regex = "[^\\/*+!?;]";
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
        } else return "no forbidden characters";
    }

    @Override
    public String controlEmail(String email) {
        String regex = "([a-zA-Z0-9_.+-]+)(@)([a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]{2,4})";
        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

        String result = "there is a problem with email";

        while(matcher.find()) {
            if (matcher.group(1).length() > 2) {
                if (matcher.group(3).length() > 5) {
                    result = "email parameters are ok";
                } else result = "There is a problem with email part after @";
            }else result ="There is a problem with email part before @";
        }return result;
    }


    @Override
    public User findUserByEmail(Login login) {
        User user = loginRepository.findUserByEmail(login.getEmail());
        if(user != null){
               if(user.getPassword().equals(login.getPassword())){
                    return user;
               }else {
//                   System.out.println("wrong password");
                   return null;
               }
        }else {
            System.out.println("Users email not found.");
            return null;
        }
    }

    @Override
    public Employee findEmployeeByEmail(Login login) {
        Employee employee = loginRepository.findEmployeeByEmail(login.getEmail());
        if(employee != null){
            if(employee.getPassword().equals(login.getPassword())){
                return employee;
            }else {
//                   System.out.println("wrong password");
                return null;
            }
        }else {
            System.out.println("Employees email not found.");
            return null;
        }
    }


}
