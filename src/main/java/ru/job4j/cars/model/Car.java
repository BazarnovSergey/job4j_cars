package ru.job4j.cars.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Модель данных автомобиля
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table(name = "car")
public class Car {
    /**
     * Идентификатор автомобиля
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    /**
     * Название автомобиля
     */
    private String name;

    /**
     * Двигатель автомобиля
     *
     * @see ru.job4j.cars.model.Engine
     */
    @ToString.Exclude
    @ManyToOne()
    @JoinColumn(name = "engine_id")
    private Engine engine;

    /**
     * Водитель автомобиля
     *
     * @see ru.job4j.cars.model.Driver
     */
    @ToString.Exclude
    @ManyToOne()
    @JoinColumn(name = "driver_id")
    private Driver driver;

    /**
     * Список владельцев автомобиля
     */
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "history_owner", joinColumns = {
            @JoinColumn(name = "car_id", nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "driver_id", nullable = false, updatable = false)})
    @ToString.Exclude
    private Set<Driver> drivers = new HashSet<>();

}
