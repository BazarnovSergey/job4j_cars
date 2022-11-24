package ru.job4j.cars.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Engine;

import static org.assertj.core.api.Assertions.assertThat;

class EngineRepositoryTest {

    private static final StandardServiceRegistry REGISTRY = new StandardServiceRegistryBuilder().configure().build();
    private static final SessionFactory SESSION_FACTORY = new MetadataSources(REGISTRY).buildMetadata().buildSessionFactory();

    @BeforeEach
    public void wipeAutoEngineTable() {
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            session.createQuery("delete from Engine ").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenAddEngineThenDataBaseHasSameEngine() {
        EngineRepository engineStore = new EngineRepository(new CrudRepository(SESSION_FACTORY));
        Engine engine = Engine.builder().name("1.6").build();
        engineStore.add(engine);
        Engine result = engineStore.findById(engine.getId()).get();
        assertThat(result.getName()).isEqualTo(engine.getName());
    }

    @Test
    public void whenFindByIdThenDataBaseReturnOptionalEngine() {
        EngineRepository engineStore = new EngineRepository(new CrudRepository(SESSION_FACTORY));
        Engine engine = Engine.builder().name("1.6").build();
        Engine engine2 = Engine.builder().name("2.4").build();
        Engine engine3 = Engine.builder().name("3.0").build();
        engineStore.add(engine);
        engineStore.add(engine2);
        engineStore.add(engine3);
        Engine result = engineStore.findById(engine2.getId()).get();
        assertThat(result.getName()).isEqualTo(engine2.getName());
    }

}