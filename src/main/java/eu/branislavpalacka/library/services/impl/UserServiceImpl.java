package eu.branislavpalacka.library.services.impl;

import eu.branislavpalacka.library.domain.User;
import eu.branislavpalacka.library.repository.UserRepository;
import eu.branislavpalacka.library.services.api.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    public final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.getAll();
    }

    @Override
    public User get(int id) {
        return userRepository.get(id);
    }

    @Override
    public Integer add(User user) {
        return userRepository.add(user);
    }
}
