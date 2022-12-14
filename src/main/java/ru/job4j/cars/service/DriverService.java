package ru.job4j.cars.service;

import ru.job4j.cars.model.Driver;

import java.util.Optional;

public interface DriverService {

    public Driver add(Driver driver);

    public Optional<Driver> findById(int driverId);

}
