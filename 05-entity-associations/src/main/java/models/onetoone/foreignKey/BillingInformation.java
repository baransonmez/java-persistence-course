package models.onetoone.foreignKey;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "billingInformation")
public class BillingInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
