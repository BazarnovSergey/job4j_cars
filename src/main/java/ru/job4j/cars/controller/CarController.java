package ru.job4j.cars.controller;

import org.springframework.stereotype.Controller;
import ru.job4j.cars.service.CarServiceImpl;

@Controller
public class CarController {

   private final CarServiceImpl carService;

    public CarController(CarServiceImpl carService) {
        this.carService = carService;
    }
}
