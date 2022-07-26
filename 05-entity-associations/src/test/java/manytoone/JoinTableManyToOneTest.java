package manytoone;

import cases.manytoone.jointable.LeaseContract;
import cases.manytoone.jointable.Vehicle;
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
import static org.testng.Assert.assertNotNull;

public class JoinTableManyToOneTest {
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
    To perform this scenario, you need to apply the following change:

    @OneToMany(mappedBy = "vehicle", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<LeaseContract> contractHistory = new HashSet<>();
     */
    @Test
    public void joinTableTest() {
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

}