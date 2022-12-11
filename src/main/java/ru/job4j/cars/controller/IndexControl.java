package ru.job4j.cars.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.cars.model.User;


import javax.servlet.http.HttpSession;

import static ru.job4j.cars.util.CheckHttpSession.checkUserAuthorization;

@Controller
public class IndexControl {

    @GetMapping("/index")
    public String index(Model model, HttpSession httpsession) {
        User user = (User) httpsession.getAttribute("user");
        checkUserAuthorization(model, user);
        model.addAttribute("user", user);
        return "redirect:/posts";
    }

}