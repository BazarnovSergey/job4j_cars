package ru.job4j.cars.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.cars.model.*;
import ru.job4j.cars.service.CarService;
import ru.job4j.cars.service.DriverService;
import ru.job4j.cars.service.EngineService;
import ru.job4j.cars.service.PostService;

import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import static ru.job4j.cars.util.CheckHttpSession.checkUserAuthorization;

@Controller
public class PostController {

    private final PostService postService;
    private final CarService carService;
    private final EngineService engineService;
    private final DriverService driverService;


    public PostController(PostService postService, CarService carService, EngineService engineService, DriverService driverService) {
        this.postService = postService;
        this.carService = carService;
        this.engineService = engineService;
        this.driverService = driverService;
    }

    @GetMapping("/posts")
    public String allPosts(Model model, HttpSession httpSession) {
        model.addAttribute("allPosts", postService.findAll());
        User user = (User) httpSession.getAttribute("user");
        checkUserAuthorization(model, user);
        model.addAttribute("user", user);
        return "posts";
    }

    @GetMapping("/formAddPost")
    public String formAddPost(Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("post", new Post());
        checkUserAuthorization(model, user);
        model.addAttribute("user", user);
        model.addAttribute("marks", carService.getCarMarks());
        model.addAttribute("engineCapacity", engineService.getEngineCapacity());
        model.addAttribute("user", user);
        return "addNewPost";
    }

    @GetMapping("/post/{postId}")
    public String post(Model model, HttpSession httpSession,
                       @PathVariable("postId") int id) {
        Optional<Post> optionalPost = postService.findById(id);
        if (optionalPost.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Post post = optionalPost.get();
        model.addAttribute("post", optionalPost.get());
        User user = (User) httpSession.getAttribute("user");
        checkUserAuthorization(model, user);
        if (post.getUser().getId() == user.getId()) {
            return "userPost";
        }
        return "post";
    }

    @PostMapping("/createPost")
    public String createPost(@ModelAttribute("post") Post post,
                             @RequestParam("mark") String carMark, Model model,
                             @RequestParam("engineCapacity") String engineCapacity,
                             @RequestParam("file") MultipartFile file,
                             HttpSession httpSession) throws IOException {

        User user = (User) httpSession.getAttribute("user");
        checkUserAuthorization(model, user);

        Engine engine = Engine.builder()
                .name(engineCapacity)
                .build();
        engineService.add(engine);

        Driver driver = Driver.builder()
                .user(user).name(post.getCar().getDriver().getName())
                .phone(post.getCar().getDriver().getPhone())
                .build();
        driverService.add(driver);

        Car car = Car.builder()
                .name(carMark)
                .engine(engine).driver(driver)
                .build();
        carService.add(car);

        PriceHistory priceHistory = PriceHistory.builder()
                .before(0L)
                .after(post.getPrice())
                .created(LocalDateTime.now())
                .post(post)
                .build();

        post.getPriceHistory().add(priceHistory);
        post.setCar(car);
        post.setPhoto(file.getBytes());
        post.setUser(user);
        post.addPriceHistory(priceHistory);
        postService.add(post);
        return "redirect:/posts";
    }

    @GetMapping("/formUpdatePost/{postId}")
    public String formUpdatePost(Model model, HttpSession httpSession,
                                 @PathVariable("postId") int id) {
        Optional<Post> optionalPost = postService.findById(id);
        if (optionalPost.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        model.addAttribute("post", optionalPost.get());
        model.addAttribute("marks", carService.getCarMarks());
        model.addAttribute("engineCapacity", engineService.getEngineCapacity());
        User user = (User) httpSession.getAttribute("user");
        checkUserAuthorization(model, user);
        return "updatePost";
    }

    @PostMapping("/updatePost")
    public String updatePost(@ModelAttribute Post post,
                             @RequestParam("mark") String carMark,
                             @RequestParam("engineCapacity") String engineCapacity,
                             @RequestParam("file") MultipartFile file,
                             HttpSession httpSession) throws IOException {
        User user = (User) httpSession.getAttribute("user");
        post.setCreated(LocalDateTime.now());
        Engine engine = Engine.builder()
                .name(engineCapacity)
                .build();
        engineService.add(engine);

        Driver driver = Driver.builder()
                .user(post.getUser()).name(post.getCar().getDriver().getName())
                .phone(post.getCar().getDriver().getPhone())
                .build();
        driverService.add(driver);

        Car car = Car.builder()
                .name(carMark)
                .engine(engine).driver(driver)
                .build();
        carService.add(car);
        long beforePrice = postService.findById(post.getId()).get().getPrice();
        PriceHistory priceHistory = PriceHistory.builder()
                .before(beforePrice)
                .after(post.getPrice())
                .created(LocalDateTime.now())
                .post(post)
                .build();

        post.getPriceHistory().add(priceHistory);
        post.setUser(user);
        post.setCar(car);
        post.setPhoto(file.getBytes());

        postService.update(post);
        return "redirect:/posts";
    }

    @GetMapping("/posterPost/{postId}")
    public ResponseEntity<Resource> download(@PathVariable("postId") Integer postId) {
        Optional<Post> post = postService.findById(postId);
        if (post.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return post.<ResponseEntity<Resource>>map(value -> ResponseEntity.ok()
                .headers(new HttpHeaders())
                .contentLength(value.getPhoto().length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new ByteArrayResource(value.getPhoto()))).orElse(null);
    }

    @PostMapping("/formDelete")
    public String formDelete(@ModelAttribute Post post) {
        postService.delete(post);
        return "redirect:/posts";
    }

    @GetMapping("/lastDayPosts")
    public String lastDayPosts(Model model, HttpSession httpSession) {
        model.addAttribute("lastPosts", postService.getPostsFromTheLastDay());
        User user = (User) httpSession.getAttribute("user");
        checkUserAuthorization(model, user);
        model.addAttribute("user", user);
        return "lastPosts";
    }

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFoundException() {
        return "404";
    }
}
