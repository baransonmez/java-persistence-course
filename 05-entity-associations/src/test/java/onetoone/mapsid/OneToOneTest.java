package onetoone.mapsid;

import models.onetoone.mapsid.User;
import models.onetoone.mapsid.UserDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
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
    public void mapsIdExampleTEst() {
        User user;
        User user3;
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();

            user = new User("barans");
            UserDetails details = new UserDetails("photourl.com");
            user.setDetails(details);
            session.persist(user);
            session.persist(new User("ali"));

            user3 = new User("3.user");
            UserDetails user3detail = new UserDetails("3.userphotourl.com");
            user3.setDetails(user3detail);
            session.persist(user3);

            tx.commit();
        }

        try (Session session = factory.openSession()) {
            User userFromDb = session.find(User.class, user.getId());
            User user3FromDb = session.find(User.class, user3.getId());
            UserDetails user3DetailFromDb = session.find(UserDetails.class, user3.getId());
            assertNotNull(userFromDb);
            assertEquals(user3FromDb, user3DetailFromDb.getUser());
        }
    }

}