package singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("bankAccount")
public class BankAccount extends BillingDetails {
    private String IBAN;
    private String bankName;

    public BankAccount() {
    }

    public BankAccount(String IBAN, String bankName) {
        this.IBAN = IBAN;
        this.bankName = bankName;
    }

    @Override
    public void makeThePayment() {
        System.out.println("PAY WITH BANK ACCOUNT");
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "IBAN='" + IBAN + '\'' +
                ", bankName='" + bankName + '\'' +
                ", id=" + id +
                '}';
    }

    public String getIBAN() {
        return IBAN;
    }

    public String getBankName() {
        return bankName;
    }
}
