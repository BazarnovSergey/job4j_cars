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

    /**
     * Идентификатор владельца
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    /**
     * Имя владельца
     */
    private String name;

    /**
     * Телефон водителя
     */
    private String phone;

    /**
     * Пользователь
     *
     * @see ru.job4j.cars.model.User
     */
    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setUser(User user) {
        user.setDriver(this);
        this.user = user;
    }

}
