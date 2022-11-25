package ru.job4j.cars.model;

import lombok.*;

import javax.persistence.*;

/**
 * Модель данных владельца
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "driver")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private String name;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
