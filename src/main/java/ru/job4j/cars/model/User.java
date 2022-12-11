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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String login;
    private String password;

    @ToString.Exclude
    @ManyToMany(mappedBy = "participates", fetch = FetchType.EAGER)
    private List<Post> posts = new ArrayList<>();

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