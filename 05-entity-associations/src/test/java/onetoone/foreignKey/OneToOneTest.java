package onetoone.foreignKey;

import cases.onetoone.foreignKey.BillingInformation;
import cases.onetoone.foreignKey.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.testng.Assert.assertNotNull;

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
    public void foreignKeyColumnTest() {
        User user = new User("barans");
        User user2 = new User("barans2");
        User user3 = new User("barans3");
        BillingInformation billing = new BillingInformation("1111 2222 3333 4444", LocalDate.now().plusDays(5), user);
        BillingInformation billing2 = new BillingInformation("3333 2222 5555 4444", LocalDate.now().plusDays(5), user2);
        user.setBillingInfo(billing);
        user2.setBillingInfo(billing2);
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(user);
            session.persist(user3);
            session.persist(user2);
            tx.commit();
        }

        try (Session session = factory.openSession()) {
            User user2FromDb = session.find(User.class, user2.getId());
            assertNotNull(user2FromDb);
        }
    }

}