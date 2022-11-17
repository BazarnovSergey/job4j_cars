package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class PostRepository {

    private final CrudRepository crudRepository;

    /**
     * Метод возвращает из базы данных все посты за последний день
     * currentTime - текущее время
     * currentTimeMinus24Hours - текущее время минус 24 часа
     *
     * @return коллекция List с постами
     */
    public List<Post> getPostsFromTheLastDay() {
        var currentTime = LocalDateTime.now();
        var currentTimeMinus24Hours = currentTime.minusHours(24);
        return crudRepository.query(
                "select p from Post p where p.created between "
                        + ":fCurrentTime and :fCurrentTimeMinus24Hours", Post.class,
                Map.of("fCurrentTime", currentTime,
                        "fCurrentTimeMinus24Hours", currentTimeMinus24Hours)
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
                " select from Post p join fetch Car c where lower(c.name) like lower('%:fCarBrand%')",
                Post.class, Map.of("fCarBrand", carBrand));
    }

}