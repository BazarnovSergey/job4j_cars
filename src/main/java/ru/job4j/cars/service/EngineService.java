package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.repository.EngineRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
@AllArgsConstructor
@Slf4j
public class EngineService implements EngineServiceImpl {

    private final static List<String> ENGINE_CAPACITY = List.of(
            "1.4", "1.5", "1.6", "1.8", "2.0", "2.2",
            "2.5", "3.0", "3.5"
    );

    private final EngineRepositoryImpl engineRepository;

    public Engine add(Engine engine) {
        return engineRepository.add(engine);
    }

    public List<String> getEngineCapacity() {
        return new ArrayList<>(ENGINE_CAPACITY);
    }
}