package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Хранилище постов
 * @see ru.job4j.cars.model.Post
 */

@Repository
@AllArgsConstructor
public class PostRepository {

    private final CrudRepository crudRepository;

    /**
     * Метод добавляет пост в таблицу auto_post
     *
     * @param post - пост
     * @return объект Post
     */
    public Post add(Post post) {
        crudRepository.run(session -> session.persist(post));
        return post;
    }

    /**
     * Метод находит в базе данных пост по id
     *
     * @param postId - id поста
     * @return Optional<Post>
     */
    public Optional<Post> findById(int postId) {
        return crudRepository.optional(
                "select p from Post p where p.id = :fId", Post.class,
                Map.of("fId", postId)
        );
    }

    /**
     * Метод возвращает из базы данных все посты за последний день
     * currentTime - текущее время
     * currentTimeMinus24Hours - текущее время минус 24 часа
     *
     * @return коллекция List с постами
     */
    public List<Post> getPostsFromTheLastDay() {
        var currentTime = LocalDateTime.now();
        var currentTimeMinus24Hours = LocalDateTime.now().minusHours(24);
        return crudRepository.query(
                "FROM Post WHERE created BETWEEN :fCurrentTimeMinus24Hours AND :fCurrentTime", Post.class,
                Map.of("fCurrentTimeMinus24Hours", currentTimeMinus24Hours,
                        "fCurrentTime", currentTime)
        );
    }

    /**
     * Метод возвращает из базы данных все посты содержащие фото
     *
     * @return коллекция List с постами
     */
    public List<Post> getPostsWithPhoto() {
        return crudRepository.query(
                "select p from Post p where p.photo is not null", Post.class);
    }

    /**
     * Метод возвращает из базы данных посты с авто определенной марки
     *
     * @param carBrand марка авто
     * @return коллекция List с постами
     */

    public List<Post> getPostsWithCertainBrandOfCar(String carBrand) {
        return crudRepository.query(
                "select p from Post p join fetch p.car c where c.name = :fCarBrand",
                Post.class, Map.of("fCarBrand", carBrand));
    }
}