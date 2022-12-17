package ru.job4j.cars.service;

import ru.job4j.cars.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    public List<Post> findAll();
    public Post add(Post post);
    public Optional<Post> findById(int id);
    public void delete(Post post);
    public void update(Post post);
    public List<Post> getPostsFromTheLastDay();
}
