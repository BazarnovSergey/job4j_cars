package ru.job4j.cars.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.User;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UserRepositoryTest {

    private static final StandardServiceRegistry REGISTRY = new StandardServiceRegistryBuilder().configure().build();
    private static final SessionFactory SESSION_FACTORY = new MetadataSources(REGISTRY).buildMetadata().buildSessionFactory();

    @BeforeEach
    public void wipeAutoUserTable() {
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenAddUserThenDataBaseHasSameUser() {
        UserRepository userRepository = new UserRepository(new CrudRepository(SESSION_FACTORY));
        User user = User.builder().login("admin").build();
        userRepository.create(user);
        User result = userRepository.findById(user.getId()).get();
        assertThat(result.getLogin()).isEqualTo(user.getLogin());
    }

    @Test
    public void whenUpdateUserThenDataBaseHasSameUser() {
        UserRepository userRepository = new UserRepository(new CrudRepository(SESSION_FACTORY));
        User user = User.builder().login("admin").build();
        userRepository.create(user);
        user.setLogin("guest");
        userRepository.update(user);
        User result = userRepository.findById(user.getId()).get();
        assertThat(result.getLogin()).isEqualTo(user.getLogin());
    }

    @Test
    public void whenDeleteUser() {
        UserRepository userRepository = new UserRepository(new CrudRepository(SESSION_FACTORY));
        User user = User.builder().login("admin").password("password").build();
        userRepository.create(user);
        userRepository.delete(user.getId());
        List<User> result = userRepository.findByLikeLogin(user.getLogin());
        assertThat(result).isEmpty();
    }

    @Test
    public void whenFindAllOrderById() {
        UserRepository userRepository = new UserRepository(new CrudRepository(SESSION_FACTORY));
        User user1 = User.builder().login("admin").build();
        User user2 = User.builder().login("guest").build();
        User user3 = User.builder().login("user").build();
        userRepository.create(user1);
        userRepository.create(user2);
        userRepository.create(user3);
        List<User> sortedUserListDb = userRepository.findAllOrderById();
        assertThat(sortedUserListDb.get(0).getId()).isEqualTo(user1.getId());
        assertThat(sortedUserListDb.get(1).getId()).isEqualTo(user2.getId());
        assertThat(sortedUserListDb.get(2).getId()).isEqualTo(user3.getId());
    }

    @Test
    public void whenFindByLikeLogin() {
        UserRepository userRepository = new UserRepository(new CrudRepository(SESSION_FACTORY));
        User user1 = User.builder().login("administrator").password("password").build();
        User user2 = User.builder().login("super_admin").password("password").build();
        User user3 = User.builder().login("user").password("password").build();
        userRepository.create(user1);
        userRepository.create(user2);
        userRepository.create(user3);
        List<User> resultDb = userRepository.findByLikeLogin("adm");
        assertThat(resultDb.size()).isEqualTo(2);
    }

    @Test
    public void whenFindByLogin() {
        UserRepository userRepository = new UserRepository(new CrudRepository(SESSION_FACTORY));
        User user1 = User.builder().login("admin").build();
        User user2 = User.builder().login("guest").build();
        User user3 = User.builder().login("user").build();
        userRepository.create(user1);
        userRepository.create(user2);
        userRepository.create(user3);
        User result = userRepository.findByLogin(user2.getLogin()).get();
        assertThat(result.getLogin()).isEqualTo(user2.getLogin());
    }

}