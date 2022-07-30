package joined;

import jakarta.persistence.Entity;

@Entity
public class BankAccount extends BillingDetails {
    private String IBAN;
    private String bankName;

    public BankAccount() {
    }

    public BankAccount(String ownerName, String IBAN, String bankName) {
        this.IBAN = IBAN;
        this.bankName = bankName;
        this.ownerName = ownerName;
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
