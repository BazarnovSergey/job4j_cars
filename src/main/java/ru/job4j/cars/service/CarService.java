package ru.job4j.cars.service;

import ru.job4j.cars.model.Car;

import java.util.List;

public interface CarService {

    public Car add(Car car);

    public List<String> getCarMarks();
}
