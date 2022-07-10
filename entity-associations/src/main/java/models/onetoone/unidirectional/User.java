package models.onetoone.unidirectional;

import jakarta.persistence.*;

@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @OneToOne(optional = false)
    @PrimaryKeyJoinColumn
    private BillingInformation billingInfo;

    public User() {
    }

    public User(String userName, BillingInformation billingInfo) {
        this.userName = userName;
        this.billingInfo = billingInfo;
    }

    public Long getId() {
        return id;
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
