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

    private String name;
    private String text;
    private LocalDateTime created = LocalDateTime.now();

    @Column(name = "sale_status")
    private Boolean saleStatus = false;

    @ManyToOne
    @JoinColumn(name = "auto_user_id")
    private User user;
    private byte[] photo;
    private Long price;

    @ToString.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PriceHistory> priceHistory = new ArrayList<>();

    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinTable(
            name = "participates",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> participates = new ArrayList<>();

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
