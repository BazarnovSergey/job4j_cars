package ru.job4j.cars.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Модель данных объявления
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "auto_post")
public class Post {

    /**
     * Идентификатор объявления
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    /**
     * Название объявления
     */
    private String name;

    /**
     * Описание объявления
     */
    private String text;

    /**
     * Дата создания объявления
     */
    private LocalDateTime created = LocalDateTime.now();

    /**
     * Состояние объявление
     * false - автомобиль продается
     * true - автомобиль продан
     */
    @Column(name = "sale_status")
    private Boolean saleStatus = false;

    /**
     * Пользователь
     *
     * @see ru.job4j.cars.model.User
     */
    @ManyToOne
    @JoinColumn(name = "auto_user_id")
    private User user;

    /**
     * Фото автомобиля
     */
    private byte[] photo;

    /**
     * Цена автомобиля
     */
    private Long price;

    /**
     * История изменения цены на автомобиль
     */
    @ToString.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PriceHistory> priceHistory = new ArrayList<>();

    /**
     * Подписки к объявлению
     */
    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinTable(
            name = "participates",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> participates = new ArrayList<>();

    /**
     * Автомобиль
     *
     * @see ru.job4j.cars.model.Car
     */
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    private Car car;

    public void addPriceHistory(PriceHistory ph) {
        priceHistory.add(ph);
        ph.setPost(this);
    }

}
