package ru.job4j.cars.model;

import lombok.*;

import javax.persistence.*;

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
     * @see ru.job4j.cars.model.Driver
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    /**
     * Название двигателя
     */
    private String name;

}
