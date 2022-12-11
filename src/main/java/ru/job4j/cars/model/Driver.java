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

    private String phone;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setUser(User user) {
        user.setDriver(this);
        this.user = user;
    }

}
