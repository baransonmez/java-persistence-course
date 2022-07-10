package onetoone.unidirectional;

import models.onetoone.unidirectional.BillingInformation;
import models.onetoone.unidirectional.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.testng.Assert.*;

public class OneToOneTest {
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
    public void createUser() {
        BillingInformation billing = new BillingInformation("1111 2222 3333 4444", LocalDate.now().plusDays(5));
        User user = new User("barans", billing);
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(billing);
            session.persist(user);
            tx.commit();
        }

        try (Session session = factory.openSession()) {
            User userFromDb = session.find(User.class, user.getId());
            System.out.println(userFromDb);
            assertNotNull(userFromDb);
        }
    }

}