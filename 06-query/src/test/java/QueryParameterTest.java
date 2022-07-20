import models.Condition;
import models.Vehicle;
import models.VehicleType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class QueryParameterTest {
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
    public void queryVehicleTest() {
        List<Vehicle> list;
        try (Session session = factory.openSession()) {
            list = session
                    .createQuery("select v from vehicles v", Vehicle.class)
                    .list();
            for (Vehicle v : list) {
                System.out.println(v);
            }
        }
    }

    @Test
    public void queryParameterStringVehicleTest() {
        List<Vehicle> list;
        try (Session session = factory.openSession()) {
            String modelParameter = "model-3";
            list = session
                    .createQuery("select v from vehicles v where v.model=:model", Vehicle.class).setParameter("model", modelParameter)
                    .list();
            for (Vehicle v : list) {
                System.out.println(v);
            }
            assertEquals(list.size(), 1);
            assertEquals(list.get(0).getModel(), modelParameter);
        }
    }

    @Test
    public void queryParameterEnumStringVehicleTest() {
        List<Vehicle> list;
        try (Session session = factory.openSession()) {
            Condition conditionParameter = Condition.NEAR_NEW;
            list = session
                    .createQuery("select v from vehicles v where v.condition=:condition", Vehicle.class).setParameter("condition", conditionParameter)
                    .list();
            for (Vehicle v : list) {
                System.out.println(v);
            }
            assertEquals(list.size(), 2);
            list.forEach(vehicle -> assertEquals(vehicle.getCondition(), conditionParameter));
        }
    }

    @Test
    public void queryParameterEnumOrdinalVehicleTest() {
        List<Vehicle> list;
        try (Session session = factory.openSession()) {
            VehicleType typeParameter = VehicleType.HATCHBACK;
            list = session
                    .createQuery("select v from vehicles v where v.type=:type", Vehicle.class).setParameter("type", typeParameter)
                    .list();
            for (Vehicle v : list) {
                System.out.println(v);
            }
            assertEquals(list.size(), 2);
            list.forEach(vehicle -> assertEquals(vehicle.getType(), typeParameter));
        }
    }

    @Test
    public void queryParameterNumberGreaterThanTest() {
        List<Vehicle> list;
        try (Session session = factory.openSession()) {
            double dailyPrice = 110.5;
            list = session
                    .createQuery("select v from vehicles v where v.dailyPrice>:dailyPrice", Vehicle.class).setParameter("dailyPrice", dailyPrice)
                    .list();
            for (Vehicle v : list) {
                System.out.println(v);
            }
            assertEquals(list.size(), 2);
            list.forEach(vehicle -> assertTrue(vehicle.getDailyPrice() > dailyPrice));
        }
    }

}