package ru.job4j.cars.controller;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.Model;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Driver;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.service.CarService;
import ru.job4j.cars.service.DriverService;
import ru.job4j.cars.service.EngineService;
import ru.job4j.cars.service.PostService;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

class PostControllerTest {

    @Test
    public void whenPosts() {
        Engine engine = Engine.builder()
                .id(1)
                .name("name")
                .build();
        Driver driver = Driver.builder()
                .id(1)
                .name("DriverName")
                .phone("12345678")
                .build();
        Car car = Car.builder()
                .id(1)
                .name("carName")
                .engine(engine)
                .driver(driver)
                .build();
        List<Post> posts = Arrays.asList(Post.builder()
                        .id(1)
                        .name("name")
                        .text("text")
                        .photo(new byte[10])
                        .created(LocalDateTime.now())
                        .car(car)
                        .build(),
                Post.builder()
                        .id(2)
                        .name("name")
                        .text("text")
                        .photo(new byte[10])
                        .created(LocalDateTime.now())
                        .car(car)
                        .build()
        );
        Model model = mock(Model.class);
        PostService postService = mock(PostService.class);
        HttpSession session = mock(HttpSession.class);
        when(postService.findAll()).thenReturn(posts);
        CarService carService = mock(CarService.class);
        EngineService engineService = mock(EngineService.class);
        DriverService driverService = mock(DriverService.class);
        PostController postController = new PostController(
                postService,
                carService,
                engineService,
                driverService
        );
        String page = postController.allPosts(model, session);
        verify(model).addAttribute("allPosts", posts);
        assertThat(page, is("posts"));
    }

    @Test
    public void whenCreatePost() throws IOException {
        Engine engine = Engine.builder()
                .id(1)
                .name("name")
                .build();
        Driver driver = Driver.builder()
                .id(1)
                .name("DriverName")
                .phone("12345678")
                .build();
        Car car = Car.builder()
                .id(1)
                .name("carName")
                .engine(engine)
                .driver(driver)
                .build();
        Post input = Post.builder()
                .id(2)
                .name("name")
                .text("text")
                .photo(new byte[10])
                .created(LocalDateTime.now())
                .car(car)
                .build();
        Model model = mock(Model.class);
        MockMultipartFile multipartFile = new MockMultipartFile("file", new byte[10]);
        HttpSession session = mock(HttpSession.class);
        PostService postService = mock(PostService.class);
        CarService carService = mock(CarService.class);
        EngineService engineService = mock(EngineService.class);
        DriverService driverService = mock(DriverService.class);

        PostController postController = new PostController(
                postService,
                carService,
                engineService,
                driverService
        );
        String page = postController.createPost(input, car.getName(), model,
                "1.6", multipartFile, session);
        verify(postService).add(input);
        assertThat(page, is("redirect:/posts"));
    }

    @Test
    public void whenUpdatePost() throws IOException {
        Engine engine = Engine.builder()
                .id(1)
                .name("name")
                .build();
        Driver driver = Driver.builder()
                .id(1)
                .name("DriverName")
                .phone("12345678")
                .build();
        Car car = Car.builder()
                .id(1)
                .name("carName")
                .engine(engine)
                .driver(driver)
                .build();
        Post input = Post.builder()
                .id(2)
                .name("name")
                .text("text")
                .photo(new byte[10])
                .price(100L)
                .created(LocalDateTime.now())
                .car(car)
                .build();
        Post input2 = Post.builder()
                .id(2)
                .name("name")
                .text("text")
                .photo(new byte[10])
                .price(100L)
                .created(LocalDateTime.now())
                .car(car)
                .build();
        Model model = mock(Model.class);
        MockMultipartFile multipartFile = new MockMultipartFile("file", new byte[10]);
        HttpSession session = mock(HttpSession.class);
        PostService postService = mock(PostService.class);
        when(postService.findById(input2.getId())).thenReturn(Optional.of(input2));
        CarService carService = mock(CarService.class);
        EngineService engineService = mock(EngineService.class);
        DriverService driverService = mock(DriverService.class);

        PostController postController = new PostController(
                postService,
                carService,
                engineService,
                driverService
        );
        postController.createPost(input, car.getName(), model,
                "1.6", multipartFile, session);
        String page = postController.updatePost(input2, car.getName(), "1.6"
                , multipartFile, session);
        verify(postService).update(input2);
        assertThat(page, is("redirect:/posts"));
    }

    @Test
    public void whenFormAddPost() {
        List<String> carMarks = List.of(
                "Audi", "BMW", "KIA", "Skoda", "Toyota", "ВАЗ",
                "Volkswagen", "Volvo", "Opel", "Lexus", "Mazda", "Mercedes"
        );
        List<String> engineCapacity = List.of(
                "1.4", "1.5", "1.6", "1.8", "2.0", "2.2",
                "2.5", "3.0", "3.5"
        );
        Model model = mock(Model.class);
        HttpSession session = mock(HttpSession.class);
        PostService postService = mock(PostService.class);
        CarService carService = mock(CarService.class);
        EngineService engineService = mock(EngineService.class);
        DriverService driverService = mock(DriverService.class);
        when(carService.getCarMarks()).thenReturn(carMarks);
        when(engineService.getEngineCapacity()).thenReturn(engineCapacity);
        PostController postController = new PostController(
                postService,
                carService,
                engineService,
                driverService
        );
        String page = postController.formAddPost(model,session);
        verify(model).addAttribute("marks", carMarks);
        verify(model).addAttribute("engineCapacity", engineCapacity);
        assertThat(page, is("addNewPost"));
    }

}