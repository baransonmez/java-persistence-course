package manytomany;

import cases.manytomany.simple.Tag;
import cases.manytomany.simple.Vehicle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ManyToManySimpleTest {
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
    public void simpleManyToManyExampleTest() {

        Tag tag1 = new Tag("tag-1");
        Tag tag2 = new Tag("tag-2");
        Vehicle vehicle1 = new Vehicle("vehicle-model-1");
        Vehicle vehicle2 = new Vehicle("vehicle-model-2");
        tag1.addVehicle(vehicle1);
        tag2.addVehicle(vehicle1);

        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(tag1);
            session.persist(tag2);
            session.persist(vehicle2);
            tx.commit();
        }

        try (Session session = factory.openSession()) {
            Tag tagFromDB = session.find(Tag.class, tag1.getId());
            Assert.assertNotNull(tagFromDB.getVehicles());

            Vehicle vehicle1FromDB = session.find(Vehicle.class, vehicle1.getId());
            Assert.assertEquals(vehicle1FromDB.getTags().size(), 2);

            Vehicle vehicle2FromDB = session.find(Vehicle.class, vehicle2.getId());
            Assert.assertEquals(vehicle2FromDB.getTags().size(), 0);
        }

    }

}