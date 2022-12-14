package ru.job4j.cars.repository;

import ru.job4j.cars.model.Driver;

import java.util.Optional;

public interface DriverRepositoryImpl {

    public Driver add(Driver driver);

    public Optional<Driver> findById(int driverId);
}
