package mappedsuperclass;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

@Entity
public class CreditCard extends BillingDetails {
    @NotNull
    protected String cardNumber;
    @NotNull
    protected String expMonth;
    @NotNull
    protected String expYear;

    public CreditCard(String ownerName, String cardNumber, String expMonth, String expYear) {
        this.cardNumber = cardNumber;
        this.expMonth = expMonth;
        this.expYear = expYear;
        this.ownerName = ownerName;
    }

    public CreditCard() {
    }

    @Override
    public void makeThePayment() {
        System.out.println("PAY WITH CREDIT CARD");
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", expMonth='" + expMonth + '\'' +
                ", expYear='" + expYear + '\'' +
                ", id=" + id +
                '}';
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public String getExpYear() {
        return expYear;
    }
}
