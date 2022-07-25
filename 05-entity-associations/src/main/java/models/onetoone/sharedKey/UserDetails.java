package models.onetoone.sharedKey;

import jakarta.persistence.*;

@Entity(name = "userDetails")
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String profilePhotoURL;

    public UserDetails() {
    }

    public UserDetails(String profilePhotoURL) {
        this.profilePhotoURL = profilePhotoURL;
    }

    public Long getId() {
        return id;
    }

}
