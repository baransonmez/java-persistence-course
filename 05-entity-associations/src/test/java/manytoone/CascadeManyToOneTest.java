package manytoone;

import jakarta.persistence.PersistenceException;
import models.manytoone.cascade.LeaseContract;
import models.manytoone.cascade.Vehicle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

import static org.testng.Assert.*;

public class CascadeManyToOneTest {
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

    /*
    Returning the current collection in getters is not suitable for software practice.
    Because where we use Getter, we don't actually plan to make any changes, but only to read.
    If we made it open to change after this get() function, we would not be sure where this field has changed.
    We return a copy of the current collection inside get() to ensure that the get() function is only used for the read operation.

    To perform this scenario, you need to apply the following change:

    @OneToMany(mappedBy = "vehicle", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<LeaseContract> contractHistory = new HashSet<>();
    */
    @Test
    public void getterTest() {
        Vehicle vehicle = new Vehicle("Mercedes A180");
        LeaseContract firstContract = new LeaseContract(LocalDateTime.now().minusDays(25), LocalDateTime.now().minusDays(20), vehicle);
        LeaseContract secondContract = new LeaseContract(LocalDateTime.now().minusDays(20), LocalDateTime.now().minusDays(15), vehicle);
        LeaseContract thirdContract = new LeaseContract(LocalDateTime.now().minusDays(15), LocalDateTime.now().minusDays(10), vehicle);

        vehicle.getContractHistory().add(firstContract);
        vehicle.getContractHistory().add(secondContract);
        vehicle.getContractHistory().add(thirdContract);
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            assertEquals(vehicle.getContractHistory().size(), 0);
            session.persist(vehicle);
            tx.commit();
        }

        try (Session session = factory.openSession()) {
            Vehicle vehicleFromDB = session.find(Vehicle.class, vehicle.getId());
            assertEquals(vehicleFromDB.getContractHistory().size(), 0);
        }
    }

    /*
    To perform this scenario, you need to apply the following change:

    @OneToMany(mappedBy = "vehicle", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<LeaseContract> contractHistory = new HashSet<>();
     */
    @Test
    public void cascadePersistTest() {
        Vehicle vehicle = new Vehicle("Mercedes A180");
        LeaseContract firstContract = new LeaseContract(LocalDateTime.now().minusDays(25), LocalDateTime.now().minusDays(20), vehicle);
        LeaseContract secondContract = new LeaseContract(LocalDateTime.now().minusDays(20), LocalDateTime.now().minusDays(15), vehicle);
        LeaseContract thirdContract = new LeaseContract(LocalDateTime.now().minusDays(15), LocalDateTime.now().minusDays(10), vehicle);

        vehicle.addContractToHistory(firstContract);
        vehicle.addContractToHistory(secondContract);
        vehicle.addContractToHistory(thirdContract);
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(vehicle);
            assertEquals(vehicle.getContractHistory().size(), 3);
            tx.commit();
        }

        try (Session session = factory.openSession()) {
            Vehicle vehicleFromDB = session.find(Vehicle.class, vehicle.getId());
            System.out.println(vehicleFromDB);
            assertEquals(vehicleFromDB.getContractHistory().size(), 3);

            LeaseContract firstContractFromDB = session.find(LeaseContract.class, firstContract.getId());
            System.out.println(vehicleFromDB);
            assertNotNull(firstContractFromDB.getVehicle());
        }

    }

    /*
    Hibernate does not allow us to delete vehicle directly as there are LeaseContracts still referencing Vehicle in the Database.

    To perform this scenario, you need to apply the following change:

    @OneToMany(mappedBy = "vehicle", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<LeaseContract> contractHistory = new HashSet<>();
    */
    @Test(expectedExceptions = {PersistenceException.class})
    public void removeTestFail() {
        Vehicle vehicle = new Vehicle("Mercedes A180");
        LeaseContract firstContract = new LeaseContract(LocalDateTime.now().minusDays(25), LocalDateTime.now().minusDays(20), vehicle);
        LeaseContract secondContract = new LeaseContract(LocalDateTime.now().minusDays(20), LocalDateTime.now().minusDays(15), vehicle);
        LeaseContract thirdContract = new LeaseContract(LocalDateTime.now().minusDays(15), LocalDateTime.now().minusDays(10), vehicle);

        vehicle.addContractToHistory(firstContract);
        vehicle.addContractToHistory(secondContract);
        vehicle.addContractToHistory(thirdContract);
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(vehicle);
            tx.commit();
        }

        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.remove(vehicle);
            tx.commit();
        }
    }

    /*
    To perform this scenario, you need to apply the following change:

    @OneToMany(mappedBy = "vehicle", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<LeaseContract> contractHistory = new HashSet<>();
    */
    @Test
    public void removeTestPass() {
        Vehicle vehicle = new Vehicle("Mercedes A180");
        LeaseContract firstContract = new LeaseContract(LocalDateTime.now().minusDays(25), LocalDateTime.now().minusDays(20), vehicle);
        LeaseContract secondContract = new LeaseContract(LocalDateTime.now().minusDays(20), LocalDateTime.now().minusDays(15), vehicle);
        LeaseContract thirdContract = new LeaseContract(LocalDateTime.now().minusDays(15), LocalDateTime.now().minusDays(10), vehicle);

        vehicle.addContractToHistory(firstContract);
        vehicle.addContractToHistory(secondContract);
        vehicle.addContractToHistory(thirdContract);
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(vehicle);
            tx.commit();
        }

        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.remove(firstContract);
            session.remove(secondContract);
            session.remove(thirdContract);
            session.remove(vehicle);
            tx.commit();
        }
    }

    /*
    We use the orphanRemoval property to overcome the problem that
    LeaseContract remains referenced when vehicles are deleted.
    When we set this field to true, hibernate first deletes the Contracts and then the vehicle.

    To perform this scenario, you need to apply the following change:
    @OneToMany(mappedBy = "vehicle", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LeaseContract> contractHistory = new HashSet<>();
     */
    @Test
    public void orphanRemovalTest() {
        Vehicle vehicle = new Vehicle("Mercedes A180");
        LeaseContract firstContract = new LeaseContract(LocalDateTime.now().minusDays(25), LocalDateTime.now().minusDays(20), vehicle);
        LeaseContract secondContract = new LeaseContract(LocalDateTime.now().minusDays(20), LocalDateTime.now().minusDays(15), vehicle);
        LeaseContract thirdContract = new LeaseContract(LocalDateTime.now().minusDays(15), LocalDateTime.now().minusDays(10), vehicle);

        vehicle.addContractToHistory(firstContract);
        vehicle.addContractToHistory(secondContract);
        vehicle.addContractToHistory(thirdContract);
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(vehicle);
            tx.commit();
        }

        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Vehicle vehicleFromDB = session.find(Vehicle.class, vehicle.getId());
            assertNotNull(vehicleFromDB);
            assertEquals(vehicleFromDB.getContractHistory().size(), 3);
            LeaseContract firstContractFromDB = session.find(LeaseContract.class, firstContract.getId());
            assertNotNull(firstContractFromDB.getVehicle());
            session.remove(vehicleFromDB);
            transaction.commit();
        }

        try (Session session = factory.openSession()) {
            Vehicle vehicleFromDB = session.find(Vehicle.class, vehicle.getId());
            assertNull(vehicleFromDB);
            LeaseContract firstContractFromDB = session.find(LeaseContract.class, firstContract.getId());
            LeaseContract secondContractFromDB = session.find(LeaseContract.class, secondContract.getId());
            LeaseContract thirdContractFromDB = session.find(LeaseContract.class, thirdContract.getId());
            assertNull(firstContractFromDB);
            assertNull(secondContractFromDB);
            assertNull(thirdContractFromDB);
        }

    }
}