package ru.job4j.cars.service;

import ru.job4j.cars.model.Engine;

import java.util.List;

public interface EngineService {

    public List<Engine> findAll();

    public Engine add(Engine engine);

}
