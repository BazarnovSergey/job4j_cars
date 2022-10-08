package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.cars.model.User;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserRepository {
    private final SessionFactory sf;

    private static final Logger LOG = LoggerFactory.getLogger(UserRepository.class.getName());

    /**
     * Сохранить в базе.
     *
     * @param user пользователь.
     * @return пользователь с id.
     */
    public User create(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.error("Ошибка сохранения пользователя в базе данных: ", e);
            session.getTransaction().rollback();
        }
        session.close();
        return user;
    }

    /**
     * Обновить в базе пользователя.
     *
     * @param user пользователь.
     */
    public void update(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.error("Ошибка обновления пользователя в базе данных: ", e);
            session.getTransaction().rollback();
        }
        session.close();
    }

    /**
     * Удалить пользователя по id.
     *
     * @param userId ID
     */
    public void delete(int userId) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(
                            "delete User where id = :fId")
                    .setParameter("fId", userId)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.error("Ошибка удаления пользователя из базы данных: ", e);
            session.getTransaction().rollback();
        }
        session.close();
    }

    /**
     * Список пользователь отсортированных по id.
     *
     * @return список пользователей.
     */
    public List<User> findAllOrderById() {
        Session session = sf.openSession();
        List<User> userList;
        userList = (List<User>) session.createQuery(
                        "from User order by id", User.class)
                .list();
        session.close();
        return userList;
    }

    /**
     * Найти пользователя по ID
     *
     * @return пользователь.
     */
    public Optional<User> findById(int id) {
        Session session = sf.openSession();
        Optional<User> optionalUser;
        optionalUser = session.createQuery(
                        "from User where id = :fId ", User.class)
                .setParameter("fId", id)
                .uniqueResultOptional();
        session.close();
        return optionalUser;
    }

    /**
     * Список пользователей по login LIKE %key%
     *
     * @param key key
     * @return список пользователей.
     */
    public List<User> findByLikeLogin(String key) {
        List<User> userList;
        Session session = sf.openSession();
        userList = session.createQuery(
                        "from User where login like :fkey", User.class
                )
                .setParameter("fkey", "%" + key + "%")
                .list();
        session.close();
        return userList;
    }

    /**
     * Найти пользователя по login.
     *
     * @param login login.
     * @return Optional or user.
     */
    public Optional<User> findByLogin(String login) {
        Session session = sf.openSession();
        Optional<User> rsl = Optional.empty();
        try {
            session.beginTransaction();
            rsl = session.createQuery(
                            "from User u where u.login = :flogin", User.class)
                    .setParameter("flogin", login)
                    .uniqueResultOptional();
            session.getTransaction().commit();
        } catch (
                Exception e) {
            LOG.error("Ошибка поиска пользователя по login: ", e);
            session.getTransaction().rollback();
        }
        session.close();
        return rsl;
    }
}