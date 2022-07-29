import models.AccountStatus;
import models.AccountType;
import models.Address;
import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

public class PersistenceTest {
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
    public void createUserTest() {
        User user = new User("useame", AccountType.GOLD, AccountStatus.ACTIVE, new Address("emre cd", "06798", "Ankara"), new Address("emre cd", "06798", "Ankara"));

        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(user);
            tx.commit();
        }

        try (Session session = factory.openSession()) {
            User userFromDb = session.find(User.class, 1L);
            assertNotNull(userFromDb);
            System.out.println(userFromDb);
        }
    }
}