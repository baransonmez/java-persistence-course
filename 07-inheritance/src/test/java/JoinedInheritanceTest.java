import joined.BankAccount;
import joined.BillingDetails;
import joined.CreditCard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class JoinedInheritanceTest {
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
    public void joinedInheritanceTest() {
        CreditCard c1 = new CreditCard("owner1", "CARD NUMBER CARD NUMBER", "11", "25");
        CreditCard c2 = new CreditCard("owner2", "CARD NUMBER 2 CARD NUMBER 2", "1", "23");
        BankAccount b1 = new BankAccount("owner3", "IBAN IBAN 1 IBAN IBAN", "BANK NAME");
        BankAccount b2 = new BankAccount("owner4", "IBAN IBAN 2 IBAN IBAN", "BANK 2 NAME");
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();

            session.persist(c1);
            session.persist(c2);
            session.persist(b1);
            session.persist(b2);
            tx.commit();
        }

        try (Session session = factory.openSession()) {
            BillingDetails bd1 = session.find(BillingDetails.class, c1.getId());
            bd1.makeThePayment();
            BillingDetails bd2 = session.find(BillingDetails.class, c2.getId());
            bd2.makeThePayment();
            BillingDetails bd3 = session.find(BillingDetails.class, b1.getId());
            bd3.makeThePayment();
            BillingDetails bd4 = session.find(BillingDetails.class, b2.getId());
            bd4.makeThePayment();
            printInformation(bd1);
        }
    }

    private void printInformation(BillingDetails billingDetails) {
        if (billingDetails instanceof BankAccount) {
            System.out.println(((BankAccount) billingDetails).getIBAN());
        } else if (billingDetails instanceof CreditCard) {
            System.out.println(((CreditCard) billingDetails).getCardNumber());
        }
    }

}

