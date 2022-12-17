package ru.job4j.cars.service;

import ru.job4j.cars.model.User;

import java.util.Optional;
import java.util.Set;

public interface UserService {

    public Optional<User> create(User user);

    public Optional<User> findByLoginAndPassword(String login, String password);

    public Optional<User> findByLogin(String login);

    public Set<String> getAvailableZones();
}
