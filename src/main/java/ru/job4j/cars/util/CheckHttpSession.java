package ru.job4j.cars.util;

import org.springframework.ui.Model;
import ru.job4j.cars.model.User;

public final class CheckHttpSession {

    private CheckHttpSession() {
    }

    public static void checkUserAuthorization(Model model, User user) {
        if (user == null) {
            user = new User();
            user.setLogin("Гость");
        }
        model.addAttribute("user", user);
    }

}
