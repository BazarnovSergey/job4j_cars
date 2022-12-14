package ru.job4j.cars.service;

import ru.job4j.cars.model.Engine;

import java.util.List;

public interface EngineServiceImpl {

    public Engine add(Engine engine);

    public List<String> getEngineCapacity();

}
