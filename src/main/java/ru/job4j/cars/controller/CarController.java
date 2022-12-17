package ru.job4j.cars.controller;

import org.springframework.stereotype.Controller;
import ru.job4j.cars.service.CarService;

@Controller
public class CarController {

   private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }
}
