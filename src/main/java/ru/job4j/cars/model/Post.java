package ru.job4j.cars.model;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Модель данных поста
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "auto_post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    private String text;
    private LocalDateTime created = LocalDateTime.now();
    @Column(name = "auto_user_id")
    private Integer autoUserId;
    private byte[] photo;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "auto_post_id")
    private List<PriceHistory> priceHistory = new ArrayList<>();

    @ManyToMany(mappedBy = "posts")
    private List<User> users = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

}
