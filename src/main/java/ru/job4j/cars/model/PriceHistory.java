package ru.job4j.cars.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Модель данных истории изменения цен
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Builder
@Table(name = "price_history")
public class PriceHistory {

    /**
     * Идентификатор истории изменения цен
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    /**
     * Цена до изменения
     */
    private Long before;

    /**
     * Цена после изменения
     */
    private Long after;

    /**
     * Дата создания
     */
    private LocalDateTime created = LocalDateTime.now();

    /**
     * Объявление
     *
     * @see ru.job4j.cars.model.Post
     */
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "auto_post_id")
    private Post post;

}
