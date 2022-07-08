import models.Condition;
import models.VehicleType;
import models.set.Vehicle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.assertEquals;

public class MappingCollectionsSetTest {
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
    public void createVehicle() {
        Set<String> images = createImageSet();
        Vehicle vehicle = new Vehicle("Mercedes A180", 2017, 202.7, VehicleType.HATCHBACK, Condition.NEAR_NEW, images);

        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(vehicle);
            tx.commit();
        }

        List<Vehicle> list;
        try (Session session = factory.openSession()) {
            list = session
                    .createQuery("select v from vehicleWithSet v", Vehicle.class)
                    .list();
            assertEquals(list.size(), 1);
            for (Vehicle v : list) {
                System.out.println(v);

            }
        }

    }

    /*
        @ElementCollection
        @Column(name = "FILENAME")
        @CollectionTable(name = "IMAGE", joinColumns= @JoinColumn(name = "VEHICLE_ID"))
        private Set<String> images = new HashSet<>();
     */
    private HashSet<String> createImageSet() {
        HashSet<String> images = new HashSet<>();
        images.add("image-1");
        images.add("image-2");
        images.add("image-3");
        return images;
    }

}