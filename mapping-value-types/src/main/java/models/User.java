package models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @Enumerated(EnumType.ORDINAL)
    private AccountType accountType;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    public Address billingAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "DELIVERY_STREET")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "DELIVERY_ZIPCODE")),
            @AttributeOverride(name = "city", column = @Column(name = "DELIVERY_CITY"))
    })
    public Address deliveryAddress;

    @UpdateTimestamp
    protected Date lastModified;

    @CreationTimestamp
    protected Date creationDate;

    public User() {
    }

    public User(String userName, AccountType accountType, AccountStatus accountStatus, Address billingAddress, Address deliveryAddress) {
        this.userName = userName;
        this.accountType = accountType;
        this.accountStatus = accountStatus;
        this.billingAddress = billingAddress;
        this.deliveryAddress = deliveryAddress;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", accountType=" + accountType +
                ", billingAddress=" + billingAddress +
                ", deliveryAddress=" + deliveryAddress +
                ", lastModified=" + lastModified +
                ", creationDate=" + creationDate +
                '}';
    }
}
