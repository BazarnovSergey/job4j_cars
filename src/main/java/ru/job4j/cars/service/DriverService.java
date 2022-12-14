package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Driver;
import ru.job4j.cars.repository.DriverRepositoryImpl;

import java.util.Optional;


@Service
@Data
@AllArgsConstructor
@Slf4j
public class DriverService implements DriverServiceImpl {

    private final DriverRepositoryImpl driverRepository;

    public Driver add(Driver driver) {
        return driverRepository.add(driver);
    }

    public Optional<Driver> findById(int driverId) {
        return driverRepository.findById(driverId);
    }
}
