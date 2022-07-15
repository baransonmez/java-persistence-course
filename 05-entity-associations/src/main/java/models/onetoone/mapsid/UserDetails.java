package models.onetoone.mapsid;

import jakarta.persistence.*;
import models.onetoone.sharedKey.BillingInformation;

@Entity(name = "userDetails")
public class UserDetails {
    @Id
    private Long id;

    @Column
    private String profilePhotoURL;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    private User user;

    public UserDetails() {
    }

    public UserDetails(String profilePhotoURL) {
        this.profilePhotoURL = profilePhotoURL;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }
}
