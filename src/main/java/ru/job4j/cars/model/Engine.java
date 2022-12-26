package ru.job4j.cars.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Модель данных двигателя
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "engine")
public class Engine {
    /**
     * Идентификаор двигателя
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    /**
     * Название двигателя
     */
    private String name;


    /**
     * Автомобили с двигателем данного типа
     */
    @ToString.Exclude
    @OneToMany(mappedBy = "engine")
    private List<Car> cars = new ArrayList<>();

    public Engine(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
