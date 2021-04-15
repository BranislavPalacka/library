package eu.branislavpalacka.library.login;

import eu.branislavpalacka.library.domain.Employee;
import eu.branislavpalacka.library.domain.User;

public interface LoginService {

    String controlForbiddenChar(Login login);

    String controlEmail(String email);

    User findUserByEmail(Login login);

    Employee findEmployeeByEmail(String email);


}
