import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class MappingCollectionsTest {
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

    @Test
    public void readUser() {
        saveUser("username", AccountType.GOLD, AccountStatus.ACTIVE, new Address("emre cd", "06798", "Ankara"), new Address("emre cd", "06798", "Ankara"));
        List<User> list;
        try (Session session = factory.openSession()) {
            list = session
                    .createQuery("select u from users u", User.class)
                    .list();
        }
        assertEquals(list.size(), 1);
        for (User m : list) {
            System.out.println(m);
        }
    }

    @Test
    public void createVehicle() {
        HashSet<String> images = new HashSet<>();
        images.add("image-1");
        images.add("image-2");
        images.add("image-3");
        Vehicle vehicle = new Vehicle("Mercedes A180", 2017, 202.7, VehicleType.HATCHBACK, Condition.NEAR_NEW, images);

        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(vehicle);
            tx.commit();
        }

        List<Vehicle> list;
        try (Session session = factory.openSession()) {
            list = session
                    .createQuery("select v from vehicles v", Vehicle.class)
                    .list();
            assertEquals(list.size(), 1);
            for (Vehicle v : list) {
                System.out.println(v);

            }
        }

    }

    public User saveUser(String userName, AccountType accountType, AccountStatus status, Address billingAddress, Address deliveryAddress) {
        User user = new User(userName, accountType, status, billingAddress, deliveryAddress);
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(user);
            tx.commit();
        }
        return user;
    }
}