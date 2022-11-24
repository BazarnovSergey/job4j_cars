package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Driver;

import java.util.Map;
import java.util.Optional;

/**
 * Хранилище водителей
 * @see ru.job4j.cars.model.Driver
 */

@Repository
@AllArgsConstructor
public class DriverRepository {

    private final CrudRepository crudRepository;

    /**
     * Метод добавляет водителя в таблицу driver
     *
     * @param driver - водитель
     * @return объект Driver
     */
    public Driver add(Driver driver) {
        crudRepository.run(session -> session.persist(driver));
        return driver;
    }

    /**
     * Метод находит водителя в базе данных по id
     *
     * @param driverId - id авто
     * @return Optional<Driver>
     */
    public Optional<Driver> findById(int driverId) {
        return crudRepository.optional(
                "select d from Driver d where d.id = :fId", Driver.class,
                Map.of("fId", driverId)
        );
    }

}
