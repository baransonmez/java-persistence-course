package models.onetoone.sharedKey;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity(name = "billingInformation")
public class BillingInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String cardNumber;
    private LocalDate expirationDate;

    public BillingInformation() {
    }

    public BillingInformation(String cardNumber, LocalDate expirationDate) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
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
