package onetomany;

import cases.onetomany.unidirectional.regular.LeaseContract;
import cases.onetomany.unidirectional.regular.Vehicle;
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

public class UniDirectionalRegularOneToManyTest {
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
    public void deleteFirstContractTest() {
        Vehicle vehicle = new Vehicle("Mercedes A180");
        LeaseContract firstContract = new LeaseContract(LocalDateTime.now().minusDays(25), LocalDateTime.now().minusDays(20));
        LeaseContract secondContract = new LeaseContract(LocalDateTime.now().minusDays(20), LocalDateTime.now().minusDays(15));
        LeaseContract thirdContract = new LeaseContract(LocalDateTime.now().minusDays(15), LocalDateTime.now().minusDays(10));

        vehicle.addContractToHistory(firstContract);
        vehicle.addContractToHistory(secondContract);
        vehicle.addContractToHistory(thirdContract);
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(vehicle);
            assertEquals(vehicle.getContractHistory().size(), 3);
            tx.commit();
        }
        System.out.println();
        System.out.println("----------------DELETE FIRST CONTRACT-------------------");
        System.out.println();
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();

            Vehicle vehicleFromDB = session.find(Vehicle.class, vehicle.getId());
            vehicleFromDB.removeContractHistoryWithIndex(0);
            session.persist(vehicleFromDB);

            tx.commit();

        }

    }

    @Test
    public void deleteLastContractTest() {
        Vehicle vehicle = new Vehicle("Mercedes A180");
        LeaseContract firstContract = new LeaseContract(LocalDateTime.now().minusDays(25), LocalDateTime.now().minusDays(20));
        LeaseContract secondContract = new LeaseContract(LocalDateTime.now().minusDays(20), LocalDateTime.now().minusDays(15));
        LeaseContract thirdContract = new LeaseContract(LocalDateTime.now().minusDays(15), LocalDateTime.now().minusDays(10));

        vehicle.addContractToHistory(firstContract);
        vehicle.addContractToHistory(secondContract);
        vehicle.addContractToHistory(thirdContract);
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(vehicle);
            assertEquals(vehicle.getContractHistory().size(), 3);
            tx.commit();
        }
        System.out.println();
        System.out.println("----------------DELETE LAST ITEM-------------------");
        System.out.println();
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();

            Vehicle vehicleFromDB = session.find(Vehicle.class, vehicle.getId());
            vehicleFromDB.removeContractHistoryWithIndex(vehicleFromDB.getContractHistory().size() - 1);
            session.persist(vehicleFromDB);

            tx.commit();

        }

    }

    @Test
    public void insertNewContract() {
        Vehicle vehicle = new Vehicle("Mercedes A180");
        LeaseContract firstContract = new LeaseContract(LocalDateTime.now().minusDays(25), LocalDateTime.now().minusDays(20));
        LeaseContract secondContract = new LeaseContract(LocalDateTime.now().minusDays(20), LocalDateTime.now().minusDays(15));
        LeaseContract thirdContract = new LeaseContract(LocalDateTime.now().minusDays(15), LocalDateTime.now().minusDays(10));

        vehicle.addContractToHistory(firstContract);
        vehicle.addContractToHistory(secondContract);
        vehicle.addContractToHistory(thirdContract);
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(vehicle);
            assertEquals(vehicle.getContractHistory().size(), 3);
            tx.commit();
        }
        System.out.println();
        System.out.println("----------------INSERT NEW CONTRACT-------------------");
        System.out.println();
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();

            Vehicle vehicleFromDB = session.find(Vehicle.class, vehicle.getId());
            vehicleFromDB.addContractToHistory(new LeaseContract(LocalDateTime.now().minusDays(10), LocalDateTime.now().minusDays(5)));
            session.persist(vehicleFromDB);

            tx.commit();

        }

    }
}