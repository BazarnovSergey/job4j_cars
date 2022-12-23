package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class SimplePostService implements PostService {

    private final PostRepository postRepository;

    /**
     * Метод находит вcе объявления в базе данных
     *
     * @return List<Post>
     */
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    /**
     * Метод добавляет пост в таблицу auto_post
     *
     * @param post - пост
     * @return объект Post
     */
    public Post add(Post post) {
        return postRepository.add(post);
    }

    /**
     * Метод находит в базе данных пост по id
     *
     * @param id - id поста
     * @return Optional<Post>
     */
    public Optional<Post> findById(int id) {
        return postRepository.findById(id);
    }

    /**
     * удаляет пост из базы данных
     *
     * @param post - объявление
     */
    public void delete(Post post) {
        postRepository.delete(post);
    }

    /**
     * обновляет информацию в объявлении
     *
     * @param post - объявление
     */
    public void update(Post post) {
        postRepository.update(post);
    }

    /**
     * Метод возвращает из базы данных все посты за последний день
     *
     * @return коллекция List с постами
     */
    public List<Post> getPostsFromTheLastDay() {
        return postRepository.getPostsFromTheLastDay();
    }

}
