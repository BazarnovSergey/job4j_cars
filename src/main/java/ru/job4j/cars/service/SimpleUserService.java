package ru.job4j.cars.service;

import org.springframework.stereotype.Service;
import ru.job4j.cars.model.User;
import ru.job4j.cars.repository.UserRepository;

import java.time.ZoneId;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class SimpleUserService implements UserService {

    private final UserRepository userRepository;

    public SimpleUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> create(User user) {
        return userRepository.create(user);
    }

    public Optional<User> findByLoginAndPassword(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password);
    }

    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public Set<String> getAvailableZones() {
        return new HashSet<String>(ZoneId.getAvailableZoneIds());
    }
}
