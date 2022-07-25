package onetoone.sharedKey;

import models.onetoone.sharedKey.User;
import models.onetoone.sharedKey.UserDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
    public void sharedPrimaryKeyExampleTest() {
        UserDetails userDetails = new UserDetails("photourl.com");
        User user;
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(userDetails);
            user = new User("barans", userDetails);
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