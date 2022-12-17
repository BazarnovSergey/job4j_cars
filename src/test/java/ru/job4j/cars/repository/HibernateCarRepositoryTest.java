package ru.job4j.cars.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Engine;

import static org.assertj.core.api.Assertions.assertThat;

class HibernateCarRepositoryTest {

    private static final StandardServiceRegistry REGISTRY = new StandardServiceRegistryBuilder().configure().build();
    private static final SessionFactory SESSION_FACTORY = new MetadataSources(REGISTRY).buildMetadata().buildSessionFactory();

    @BeforeEach
    public void wipeAutoCarTable() {
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            session.createQuery("delete from Car ").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenAddCarThenDataBaseHasSameCar() {
        HibernateCarRepository carStore = new HibernateCarRepository(new HibernateCrudRepository(SESSION_FACTORY));
        HibernateEngineRepository engineStore = new HibernateEngineRepository(new HibernateCrudRepository(SESSION_FACTORY));
        Engine engine = Engine.builder().name("2.4").build();
        engineStore.add(engine);
        Car car = Car.builder().name("Audi").engine(engine).build();
        carStore.add(car);
        Car result = carStore.findById(car.getId()).get();
        assertThat(result.getName()).isEqualTo(car.getName());
    }

    @Test
    public void whenFindByIdThenDataBaseReturnOptionalCar() {
        HibernateCarRepository cartStore = new HibernateCarRepository(new HibernateCrudRepository(SESSION_FACTORY));
        HibernateEngineRepository engineStore = new HibernateEngineRepository(new HibernateCrudRepository(SESSION_FACTORY));
        Engine engine1 = Engine.builder().name("2.4").build();
        Engine engine2 = Engine.builder().name("2.0").build();
        Engine engine3 = Engine.builder().name("1.8").build();
        engineStore.add(engine1);
        engineStore.add(engine2);
        engineStore.add(engine3);
        Car car = Car.builder().name("Audi").engine(engine1).build();
        Car car2 = Car.builder().name("BMW").engine(engine2).build();
        Car car3 = Car.builder().name("Mercedes-Benz").engine(engine3).build();
        cartStore.add(car);
        cartStore.add(car2);
        cartStore.add(car3);
        Car result = cartStore.findById(car2.getId()).get();
        assertThat(result.getName()).isEqualTo(car2.getName());
    }

}