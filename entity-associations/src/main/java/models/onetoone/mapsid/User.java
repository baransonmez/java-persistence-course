package models.onetoone.mapsid;

import jakarta.persistence.*;
import models.onetoone.sharedKey.BillingInformation;

@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private UserDetails details;

    public User() {
    }

    public User(String userName) {
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setDetails(UserDetails details) {
        if (details != null) {
            details.setUser(this);
        }
        this.details = details;
    }
}
