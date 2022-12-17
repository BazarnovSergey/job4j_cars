package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.User;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Хранилище пользователей
 *
 * @see ru.job4j.cars.model.User
 */
@Slf4j
@Repository
@AllArgsConstructor
public class HibernateUserRepository implements UserRepository {

    private final CrudRepository crudRepository;

    /**
     * Сохранить в базе.
     *
     * @param user пользователь.
     * @return пользователь с id.
     */
    public Optional<User> create(User user) {
        crudRepository.run(session -> session.persist(user));
        return Optional.of(user);
    }

    /**
     * Обновить в базе пользователя.
     *
     * @param user пользователь.
     */
    public void update(User user) {
        crudRepository.run(session -> session.merge(user));
    }

    /**
     * Удалить пользователя по id.
     *
     * @param userId ID
     */
    public void delete(int userId) {
        crudRepository.run(
                "delete from User where id = :fId",
                Map.of("fId", userId)
        );
    }

    /**
     * Список пользователь отсортированных по id.
     *
     * @return список пользователей.
     */
    public List<User> findAllOrderById() {
        return crudRepository.query("from User", User.class);
    }

    /**
     * Найти пользователя по ID
     *
     * @return пользователь.
     */
    public Optional<User> findById(int userId) {
        return crudRepository.optional(
                "from User where id = :fId", User.class,
                Map.of("fId", userId)
        );
    }

    /**
     * Список пользователей по login LIKE %key%
     *
     * @param key key
     * @return список пользователей.
     */
    public List<User> findByLikeLogin(String key) {
        return crudRepository.query(
                "from User where login like :fKey", User.class,
                Map.of("fKey", "%" + key + "%")
        );
    }

    /**
     * Найти пользователя по login.
     *
     * @param login login.
     * @return Optional or user.
     */
    public Optional<User> findByLogin(String login) {
        try {
            return crudRepository.optional(
                    "from User where login = :fLogin", User.class,
                    Map.of("fLogin", login)
            );
        } catch (NoResultException e) {
            log.error("Пользователь не найден", e);
            return Optional.empty();
        }
    }

    public Optional<User> findByLoginAndPassword(String login, String password) {
        try {
            return crudRepository.optional(
                    "from User u where u.login = :fLogin and u.password = :fPassword", User.class,
                    Map.of("fLogin", login, "fPassword", password)
            );
        } catch (Exception e) {
            log.error("Пользователь не найден", e);
            return Optional.empty();
        }
    }

}