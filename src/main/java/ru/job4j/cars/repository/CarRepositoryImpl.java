package ru.job4j.cars.repository;

import ru.job4j.cars.model.Car;

import java.util.Optional;

public interface CarRepositoryImpl {

    public Car add(Car car);

    public Optional<Car> findById(int carId);
}