package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.repository.CarRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
@AllArgsConstructor
@Slf4j
public class CarService {

    private final static List<String> CAR_MARKS = List.of(
            "Audi", "BMW", "KIA", "Skoda", "Toyota", "ВАЗ",
            "Volkswagen", "Volvo", "Opel", "Lexus", "Mazda", "Mercedes"
    );

    private final CarRepository carRepository;

    public Car add(Car car) {
        return carRepository.add(car);
    }

    public List<String> getCarMarks() {
        return new ArrayList<>(CAR_MARKS);
    }
}
