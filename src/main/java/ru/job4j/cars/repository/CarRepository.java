package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;

import java.util.Map;
import java.util.Optional;

/**
 * Хранилище авто
 * @see ru.job4j.cars.model.Car
 */

@Repository
@AllArgsConstructor
public class CarRepository {

    private final CrudRepository crudRepository;

    /**
     * Метод добавляет авто в таблицу car
     *
     * @param car - овто
     * @return объект Car
     */
    public Car add(Car car) {
        crudRepository.run(session -> session.persist(car));
        return car;
    }

    /**
     * Метод находит авто в базе данных по id
     *
     * @param carId - id авто
     * @return Optional<Car>
     */
    public Optional<Car> findById(int carId) {
        return crudRepository.optional(
                "select c from Car c where c.id = :fId", Car.class,
                Map.of("fId", carId)
        );
    }
}
