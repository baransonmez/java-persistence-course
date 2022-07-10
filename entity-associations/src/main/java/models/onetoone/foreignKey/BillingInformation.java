package models.onetoone.foreignKey;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.time.LocalDate;

@Entity(name = "billingInformation")
public class BillingInformation {
    @Id
    @GeneratedValue(generator = "billingKeyGenerator")
    @GenericGenerator(
            name = "billingKeyGenerator",
            strategy = "foreign",
            parameters = @Parameter(name = "property", value = "user")
    )
    private Long id;
    private String cardNumber;
    private LocalDate expirationDate;

    @OneToOne
    @PrimaryKeyJoinColumn
    private User user;

    public BillingInformation() {
    }

    public BillingInformation(String cardNumber, LocalDate expirationDate, User user) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.user = user;
    }

    @Override
    public String toString() {
        return "BillingInformation{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", expirationDate=" + expirationDate +
                '}';
    }

    public Long getId() {
        return id;
    }
}
