package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Engine;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Хранилище двигателей
 *
 * @see ru.job4j.cars.model.Engine
 */

@Repository
@AllArgsConstructor
public class HibernateEngineRepository implements EngineRepository {

    private final CrudRepository crudRepository;

    /**
     * Метод возвращает все модели двигаелей из базы данных
     *
     * @return List<Engine>
     */
    public List<Engine> findAll() {
        return crudRepository.query("select p from Engine p", Engine.class);
    }

    /**
     * Метод добавляет двигатель в таблицу Engine
     *
     * @param engine - двигатель
     * @return объект Engine
     */
    public Engine add(Engine engine) {
        crudRepository.run(session -> session.persist(engine));
        return engine;
    }

    /**
     * Метод находит двигатель в базе данных по id
     *
     * @param engineId - id авто
     * @return Optional<Engine>
     */
    public Optional<Engine> findById(int engineId) {
        return crudRepository.optional(
                "select e from Engine e where e.id = :fId", Engine.class,
                Map.of("fId", engineId)
        );
    }

}
