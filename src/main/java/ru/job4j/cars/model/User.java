package ru.job4j.cars.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Модель данных пользователя
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "auto_user")
public class User {
    /**
     * Идентификатор пользователя
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Логин пользователя
     */
    private String login;

    /**
     * Пароль пользователя
     */
    private String password;

    /**
     * Объявления пользователя
     */
    @ToString.Exclude
    @ManyToMany(mappedBy = "participates", fetch = FetchType.EAGER)
    private List<Post> posts = new ArrayList<>();

    /**
     * Владелец автомобиля
     *
     * @see ru.job4j.cars.model.Driver
     */
    @ToString.Exclude
    @OneToOne(
            mappedBy = "user")
    private Driver driver;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}