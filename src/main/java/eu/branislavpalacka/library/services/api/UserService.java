package eu.branislavpalacka.library.services.api;

import eu.branislavpalacka.library.domain.User;
import org.springframework.lang.Nullable;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    @Nullable
    User get(int id);

    @Nullable
    Integer add(User user); // returns generated ID
}
