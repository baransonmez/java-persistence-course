import models.Condition;
import models.VehicleType;
import models.embeddable.Image;
import models.embeddable.Vehicle;
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
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class MappingCollectionsEmbeddableSetTest {
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
        Set<Image> images = createImageSet();
        Vehicle vehicle = new Vehicle("Mercedes A180", 2017, 202.7, VehicleType.HATCHBACK, Condition.NEAR_NEW, images);

        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(vehicle);
            tx.commit();
        }

        List<Vehicle> list;
        try (Session session = factory.openSession()) {
            list = session
                    .createQuery("select v from vehicleWithObject v", Vehicle.class)
                    .list();
            assertEquals(list.size(), 1);
            for (Vehicle v : list) {
                System.out.println(v);
            }
        }
    }

    /*
    @ElementCollection
    @CollectionTable(name = "IMAGE_SET_EMBEDDABLE")
    @OrderBy("width DESC")
     */
    private HashSet<Image> createImageSet() {
        HashSet<Image> images = new HashSet<>();
        images.add(new Image("title1", "filename1.jpg", 15, 10));
        images.add(new Image("title3", "filename3.jpg", 30, 35));
        images.add(new Image("title2", "filename2.jpg", 20, 25));
        return images;
    }

}