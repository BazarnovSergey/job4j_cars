package ru.job4j.cars.repository;

import ru.job4j.cars.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    public Post add(Post post);

    public List<Post> findAll();

    public Optional<Post> findById(int postId);

    public List<Post> getPostsFromTheLastDay();

    public List<Post> getPostsWithPhoto();

    public List<Post> getPostsWithCertainBrandOfCar(String carBrand);

    public void delete(Post post);

    public void update(Post post);


}
