package manytoone;

import cases.manytoone.bidirectional.LeaseContract;
import cases.manytoone.bidirectional.Vehicle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

import static org.testng.Assert.assertEquals;

public class BidirectionalManyToOneTest {
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
        Vehicle vehicle = new Vehicle("Mercedes A180");
        LeaseContract firstContract = new LeaseContract(LocalDateTime.now().minusDays(25), LocalDateTime.now().minusDays(20), vehicle);
        LeaseContract secondContract = new LeaseContract(LocalDateTime.now().minusDays(20), LocalDateTime.now().minusDays(15), vehicle);
        LeaseContract thirdContract = new LeaseContract(LocalDateTime.now().minusDays(15), LocalDateTime.now().minusDays(10), vehicle);

        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(vehicle);
            session.persist(firstContract);
            session.persist(secondContract);
            session.persist(thirdContract);
            assertEquals(vehicle.getContractHistory().size(), 0);
            tx.commit();
        }

        try (Session session = factory.openSession()) {
            Vehicle vehicleFromDB = session.find(Vehicle.class, vehicle.getId());
            System.out.println(vehicleFromDB);
            assertEquals(vehicleFromDB.getContractHistory().size(), 3);
        }

    }

}