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

import java.util.List;

import static org.testng.Assert.assertEquals;

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

    public User saveUser(String userName, AccountType accountType, AccountStatus status, Address billingAddress, Address deliveryAddress) {
        User user = new User(userName, accountType, status, billingAddress, deliveryAddress);
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(user);
            tx.commit();
        }
        return user;
    }

    @Test
    public void readUser() {
        saveUser("username", AccountType.GOLD, AccountStatus.ACTIVE, new Address("emre cd", "06798", "Ankara"), new Address("emre cd", "06798", "Ankara"));
        List<User> list;
        try (Session session = factory.openSession()) {
            list = session
                    .createQuery("from users", User.class)
                    .list();
        }
        assertEquals(list.size(), 1);
        for (User m : list) {
            System.out.println(m);

        }
    }
}