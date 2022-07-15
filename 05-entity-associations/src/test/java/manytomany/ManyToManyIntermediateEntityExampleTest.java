package manytomany;

import models.manytomany.intermediateentity.Tag;
import models.manytomany.intermediateentity.TaggedVehicle;
import models.manytomany.intermediateentity.Vehicle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ManyToManyIntermediateEntityExampleTest {

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
    public void intermediateEntityManyToManyExampleTest() {

        Tag tag1 = new Tag("tag-1");
        Tag tag2 = new Tag("tag-2");
        Vehicle vehicle1 = new Vehicle("vehicle-model-1");
        Vehicle vehicle2 = new Vehicle("vehicle-model-2");

        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(tag1);
            session.persist(tag2);
            session.persist(vehicle1);
            session.persist(vehicle2);
            tx.commit();
        }

        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(new TaggedVehicle("barans", vehicle1, tag1));
            session.persist(new TaggedVehicle("ihsanb", vehicle1, tag2));
            session.persist(new TaggedVehicle("ibarans", vehicle2, tag2));
            tx.commit();
        }

        try (Session session = factory.openSession()) {
            Tag tagFromDB = session.find(Tag.class, tag1.getId());
            Assert.assertNotNull(tagFromDB.getVehicles());

            Vehicle vehicle1FromDB = session.find(Vehicle.class, vehicle1.getId());
            Assert.assertEquals(vehicle1FromDB.getTags().size(), 2);

            Vehicle vehicle2FromDB = session.find(Vehicle.class, vehicle2.getId());
            Assert.assertEquals(vehicle2FromDB.getTags().size(), 1);
        }

    }
}
