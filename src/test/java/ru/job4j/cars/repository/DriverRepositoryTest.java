package ru.job4j.cars.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Driver;
import ru.job4j.cars.model.User;

import static org.assertj.core.api.Assertions.assertThat;

class DriverRepositoryTest {

    private static final StandardServiceRegistry REGISTRY = new StandardServiceRegistryBuilder().configure().build();
    private static final SessionFactory SESSION_FACTORY = new MetadataSources(REGISTRY).buildMetadata().buildSessionFactory();

    @BeforeEach
    public void wipeAutoDriverTable() {
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            session.createQuery("delete from Driver ").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenAddDriverThenDataBaseHasSameDriver() {
        DriverRepository driverStore = new DriverRepository(new CrudRepository(SESSION_FACTORY));
        Driver driver = Driver.builder().name("driver").build();
        driverStore.add(driver);
        Driver result = driverStore.findById(driver.getId()).get();
        assertThat(result.getName()).isEqualTo(driver.getName());
    }

    @Test
    public void whenFindByIdThenDataBaseReturnOptionalDriver() {
        DriverRepository driverStore = new DriverRepository(new CrudRepository(SESSION_FACTORY));
        Driver driver1 = Driver.builder().name("driver1").build();
        Driver driver2 = Driver.builder().name("driver2").build();
        Driver driver3 = Driver.builder().name("driver3").build();
        driverStore.add(driver1);
        driverStore.add(driver2);
        driverStore.add(driver3);
        Driver result = driverStore.findById(driver2.getId()).get();
        assertThat(result.getName()).isEqualTo(driver2.getName());
    }

}