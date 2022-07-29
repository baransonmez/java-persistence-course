import models.Vehicle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class NativeQueryTest {
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
    public void nativeQueryVehicleTest() {
        List<Vehicle> list;
        try (Session session = factory.openSession()) {
            list = session
                    .createNativeQuery("select * from vehicles", Vehicle.class)
                    .list();
            for (Vehicle v : list) {
                System.out.println(v);
            }
        }
    }

    @Test
    public void nativeQueryParameterStringVehicleTest() {
        List<Vehicle> list;
        try (Session session = factory.openSession()) {
            String modelParameter = "model-3";
            list = session
                    .createNativeQuery("select * from vehicles  where vehicle_model=:model", Vehicle.class).setParameter("model", modelParameter)
                    .list();
            for (Vehicle v : list) {
                System.out.println(v);
            }
            assertEquals(list.size(), 1);
            assertEquals(list.get(0).getModel(), modelParameter);
        }
    }

    /*
        SQL injection example
     */
    @Test
    public void DONTnativeQueryParameterStringVehicleTestDONT() {
        List<Vehicle> list;
        try (Session session = factory.openSession()) {
            String modelParameter = "model-3";
            Transaction tx = session.beginTransaction();
            list = session
                    .createNativeQuery("select * from vehicles  where vehicle_model='" + modelParameter + "' or 1=1;", Vehicle.class)
                    .list();
            tx.commit();
            for (Vehicle v : list) {
                System.out.println(v);
            }
            assertEquals(list.size(), 4);
        }
    }

}