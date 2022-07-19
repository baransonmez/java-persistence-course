import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
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

public class CriteriaTest {
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
    public void selectAllVehiclesTest() {

        List<Vehicle> list;
        try (Session session = factory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Vehicle> query = criteriaBuilder.createQuery(Vehicle.class);
            query.select(query.from(Vehicle.class));
            list = session
                    .createQuery(query)
                    .list();
            for (Vehicle v : list) {
                System.out.println(v);
            }
            assertEquals(list.size(), 4);
        }
    }

    @Test
    public void queryWithModelFieldTest() {

        try (Session session = factory.openSession()) {
            String searchString = "model-2";
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Vehicle> query = criteriaBuilder.createQuery(Vehicle.class);
            Root<Vehicle> root = query.from(Vehicle.class);
            query.select(root).where(criteriaBuilder.equal(root.get("model"), searchString));
            Vehicle v = session
                    .createQuery(query)
                    .getSingleResult();
            assertEquals(v.getModel(), searchString);
        }
    }

    @Test
    public void queryWithEnumFieldTest() {

        try (Session session = factory.openSession()) {
            VehicleType searchType = VehicleType.SUV;
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Vehicle> query = criteriaBuilder.createQuery(Vehicle.class);
            Root<Vehicle> root = query.from(Vehicle.class);
            query.select(root).where(criteriaBuilder.equal(root.get("type"), searchType));
            Vehicle v = session
                    .createQuery(query)
                    .getSingleResult();
            assertEquals(v.getType(), searchType);
        }
    }

    @Test
    public void queryWithNumberFieldTest() {

        try (Session session = factory.openSession()) {
            double dailyPrice = 104;
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Vehicle> query = criteriaBuilder.createQuery(Vehicle.class);
            Root<Vehicle> root = query.from(Vehicle.class);
            query.select(root).where(criteriaBuilder.greaterThan(root.get("dailyPrice"), dailyPrice));
            List<Vehicle> list = session
                    .createQuery(query)
                    .list();
            assertEquals(list.size(), 3);
            list.forEach(vehicle ->
                    assertTrue(vehicle.getDailyPrice() > dailyPrice));
        }
    }
}