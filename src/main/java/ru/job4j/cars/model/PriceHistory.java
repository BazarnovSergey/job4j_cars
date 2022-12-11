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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;
    private Long before;
    private Long after;
    private LocalDateTime created = LocalDateTime.now();

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "auto_post_id")
    private Post post;

}
