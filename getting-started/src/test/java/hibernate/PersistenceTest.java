package hibernate;

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
    public User saveUser(String name) {
        User user = new User(name);
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(user);
            tx.commit();
        }
        return user;
    }
    @Test
    public void readMessage() {
        User savedUser = saveUser("İhsan Baran SÖNMEZ");
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
        assertEquals(list.get(0), savedUser);
    }
}