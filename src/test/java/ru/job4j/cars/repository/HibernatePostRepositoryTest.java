package ru.job4j.cars.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.model.Post;

import java.time.LocalDateTime;
import java.util.List;

class HibernatePostRepositoryTest {

    private static final StandardServiceRegistry REGISTRY = new StandardServiceRegistryBuilder().configure().build();
    private static final SessionFactory SESSION_FACTORY = new MetadataSources(REGISTRY).buildMetadata().buildSessionFactory();

    @BeforeEach
    public void wipeAutoPostTable() {
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            session.createQuery("delete from Post ").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenAddPostsThenDataBaseHasSamePost() {
        HibernatePostRepository postStore = new HibernatePostRepository(new HibernateCrudRepository(SESSION_FACTORY));
        Post post = Post.builder().text("text").build();
        postStore.add(post);
        Post result = postStore.findById(post.getId()).get();
        assertThat(result.getText()).isEqualTo(post.getText());
    }

    @Test
    public void whenFindByIdThenDataBaseReturnOptionalPost() {
        HibernatePostRepository postStore = new HibernatePostRepository(new HibernateCrudRepository(SESSION_FACTORY));
        Post post = Post.builder().text("text").build();
        Post post2 = Post.builder().text("text2").build();
        Post post3 = Post.builder().text("text3").build();
        postStore.add(post);
        postStore.add(post2);
        postStore.add(post3);
        Post result = postStore.findById(post2.getId()).get();
        assertThat(result.getText()).isEqualTo(post2.getText());
    }

    @Test
    public void whenGetPostsFromTheLastDay() {
        HibernatePostRepository postStore = new HibernatePostRepository(new HibernateCrudRepository(SESSION_FACTORY));
        Post post = Post.builder().created(LocalDateTime.now()).build();
        Post post2 = Post.builder().created(LocalDateTime.now().minusDays(2L)).build();
        postStore.add(post);
        postStore.add(post2);
        List<Post> result = List.of(post);
        List<Post> resultDb = postStore.getPostsFromTheLastDay();
        assertThat(resultDb.size()).isEqualTo(result.size());
    }

    @Test
    public void whenGetPostsWithPhoto() {
        HibernatePostRepository postStore = new HibernatePostRepository(new HibernateCrudRepository(SESSION_FACTORY));
        Post post = Post.builder().photo(new byte[10]).build();
        Post post2 = Post.builder().build();
        postStore.add(post);
        postStore.add(post2);
        List<Post> result = List.of(post);
        List<Post> resultDb = postStore.getPostsWithPhoto();
        assertThat(resultDb.size()).isEqualTo(result.size());
    }

    @Test
    public void whenGetPostsWithCertainBrandOfCar() {
        HibernateEngineRepository engineStore = new HibernateEngineRepository(new HibernateCrudRepository(SESSION_FACTORY));
        HibernateCarRepository carStore = new HibernateCarRepository(new HibernateCrudRepository(SESSION_FACTORY));
        HibernatePostRepository postStore = new HibernatePostRepository(new HibernateCrudRepository(SESSION_FACTORY));
        Engine engine1 = Engine.builder().name("2.4").build();
        Engine engine2 = Engine.builder().name("2.0").build();
        engineStore.add(engine1);
        engineStore.add(engine2);
        Car car = Car.builder().name("Audi").engine(engine1).build();
        Car car2 = Car.builder().name("BMW").engine(engine2).build();
        carStore.add(car);
        carStore.add(car2);
        Post post = Post.builder().car(car).build();
        postStore.add(post);
        String result = car.getName();
        List<Post> resultDb = postStore.getPostsWithCertainBrandOfCar(car.getName());
        assertThat(resultDb.get(0).getCar().getName()).isEqualTo(result);
    }

}