import models.Condition;
import models.map.Vehicle;
import models.VehicleType;
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

public class MappingCollectionsMapTest {
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
        Map<String, String> images = createImageMap();
        Vehicle vehicle = new Vehicle("Mercedes A180", 2017, 202.7, VehicleType.HATCHBACK, Condition.NEAR_NEW, images);

        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(vehicle);
            tx.commit();
        }

        List<Vehicle> list;
        try (Session session = factory.openSession()) {
            list = session
                    .createQuery("select v from vehicleWithMap v", Vehicle.class)
                    .list();
            assertEquals(list.size(), 1);
            for (Vehicle v : list) {
                System.out.println(v);

            }
        }

    }

    /*
    @ElementCollection
    @CollectionTable(name = "IMAGE")
    @Column(name = "IMAGE_PATH")
    @MapKeyColumn(name = "FILENAME")
    private Map<String, String> images = new HashMap<>();
     */
    private HashMap<String, String> createImageMap() {
        HashMap<String, String> images = new HashMap<>();
        /*
        HashMap<fileName, imageName>
         */
        images.put("image1.jpg", "image-1");
        images.put("image2.jpg", "image-2");
        images.put("image3.jpg", "image-3");
        return images;
    }

}