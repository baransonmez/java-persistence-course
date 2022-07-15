package models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class PersistenceLifeCycleTest {
    private SessionFactory factory = null;

    @BeforeClass
    public void setup() {
        StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder()
                        .configure()
                        .build();
        factory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();
    }

    @BeforeMethod
    public void cleanDB() {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            List<Vehicle> vehiclesFromDB = session
                    .createQuery("select v from vehicles v", Vehicle.class)
                    .list();

            vehiclesFromDB.forEach(
                    session::remove
            );
            tx.commit();
        }
    }

    /*
    We can see that there is no record in the database before it is persisted by the session.
     */
    @Test
    public void persistVehicleTest() {
        Vehicle vehicle = new Vehicle("Mercedes A180");

        try (Session session = factory.openSession()) {
            List<Vehicle> list = session
                    .createQuery("select v from vehicles v", Vehicle.class)
                    .list();
            assertEquals(list.size(), 0);

            Transaction tx = session.beginTransaction();
            session.persist(vehicle);
            tx.commit();
            list = session
                    .createQuery("select v from vehicles v", Vehicle.class)
                    .list();
            assertEquals(list.size(), 1);
        }

    }

    /*
    Any changes made after committing the transaction does not reflect to the database.
     */
    @Test
    public void updateDetachedObjectTest() {
        String oldModelName = "Mercedes A180";
        Vehicle vehicle = new Vehicle(oldModelName);

        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(vehicle);
            tx.commit();
            List<Vehicle> list = session
                    .createQuery("select v from vehicles v", Vehicle.class)
                    .list();
            assertEquals(list.size(), 1);
        }

        String newModelName = "Mercedes C200";
        vehicle.setModel(newModelName);

        try (Session session = factory.openSession()) {

            Vehicle vehicleFromDB = session
                    .find(Vehicle.class, vehicle.id);
            assertNotEquals(newModelName, vehicleFromDB.getModel());
        }

    }

    /*
    If the transaction is not finished and some update processes are happened after the persist operation,
    the persistence context does dirty checking after the transaction is finished, if it detects a change,
    it will update the entity's state.
     */
    @Test
    public void updateFieldInTheTransactionTest() {
        String oldModelName = "Mercedes A180";
        String newModelName = "Mercedes C200";

        Vehicle vehicle = new Vehicle(oldModelName);

        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(vehicle);
            vehicle.setModel(newModelName);
            tx.commit();
        }

        try (Session session = factory.openSession()) {
            Vehicle vehicleFromDB = session
                    .find(Vehicle.class, vehicle.id);
            assertEquals(newModelName, vehicleFromDB.getModel());
        }
    }

    /*
    In the same session, if a record is brought from the database, that record is cached.
    If it is wanted to be brought back, it is not queried in the database, it is brought from the cache.
    Caches are session specific. When the session ends, the cache deletes all records in the same way.

    You can follow the queries made by Hibernate from the console.
     */
    @Test
    public void cacheResults() {

        Vehicle vehicle = new Vehicle("Mercedes A180");

        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(vehicle);
            tx.commit();
        }

        System.out.println("Creating Session");
        try (Session session = factory.openSession()) {
            session.find(Vehicle.class, vehicle.id);
            session.find(Vehicle.class, vehicle.id);
            session.find(Vehicle.class, vehicle.id);
        }

        System.out.println("Creating Another Session");
        try (Session session = factory.openSession()) {
            session.find(Vehicle.class, vehicle.id);
            session.find(Vehicle.class, vehicle.id);
        }
    }

    /*
    If we want to update the status of the record with its current status in the database,
    we can use the session's refresh method.
     */
    @Test
    public void refreshEntityState() {
        String oldModelName = "Mercedes A180";
        Vehicle vehicle = new Vehicle(oldModelName);

        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(vehicle);
            tx.commit();
        }
        String newModelName = "New Model";
        vehicle.setModel(newModelName);
        try (Session session = factory.openSession()) {
            assertEquals(vehicle.getModel(), newModelName);
            session.refresh(vehicle);
            assertEquals(vehicle.getModel(), oldModelName);
        }
        assertEquals(vehicle.getModel(), oldModelName);
    }

}