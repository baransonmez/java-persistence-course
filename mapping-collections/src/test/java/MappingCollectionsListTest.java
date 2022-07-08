import models.Condition;
import models.list.Vehicle;
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

public class MappingCollectionsListTest {
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
        List<String> images = createImageList();
        Vehicle vehicle = new Vehicle("Mercedes A180", 2017, 202.7, VehicleType.HATCHBACK, Condition.NEAR_NEW, images);

        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(vehicle);
            tx.commit();
        }

        List<Vehicle> list;
        try (Session session = factory.openSession()) {
            list = session
                    .createQuery("select v from vehicleWithList v", Vehicle.class)
                    .list();
            assertEquals(list.size(), 1);
            for (Vehicle v : list) {
                System.out.println(v);

            }
        }

    }

    /*
      @ElementCollection
      @Column(name = "IMAGE_PATH")
      @CollectionTable(name = "IMAGE")
      @OrderColumn
      List<String> images = new Arraylist<>();
   */
    private List<String> createImageList() {
        List<String> images = new ArrayList<>();
        images.add("image-1");
        images.add("image-2");
        images.add("image-3");
        return images;
    }

}