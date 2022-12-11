package ru.job4j.cars.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;
import ru.job4j.cars.service.CarService;

import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ru.job4j.cars.util.CheckHttpSession.checkUserAuthorization;

@Controller
public class CarController {

   private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

//    @PostMapping("/createCar")
//    public String createPost(@ModelAttribute("car") Car car, Model model,
//                             HttpSession httpSession) {
//        User user = (User) httpSession.getAttribute("user");
//        checkUserAuthorization(model, user);
//        post.setPhoto(file.getBytes());
//        post.setUser(user);
//        postService.add(post);
//        return "redirect:/posts";
//    }
}
