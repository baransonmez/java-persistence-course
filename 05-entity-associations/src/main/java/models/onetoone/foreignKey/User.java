package models.onetoone.foreignKey;

import jakarta.persistence.*;

@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @OneToOne(optional = true, cascade = CascadeType.PERSIST)
    @JoinColumn
    private BillingInformation billingInfo;

    public User() {
    }

    public User(String userName) {
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setBillingInfo(BillingInformation billingInfo) {
        this.billingInfo = billingInfo;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", billingInfo=" + billingInfo +
                '}';
    }
}
