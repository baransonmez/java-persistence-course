package models.onetoone.sharedKey;

import jakarta.persistence.*;

@Entity(name = "users")
public class User {
    @Id
    private Long id;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @OneToOne(optional = false)
    @PrimaryKeyJoinColumn
    private UserDetails userDetails;

    public User() {
    }

    public User(String userName, UserDetails userDetails) {
        this.id = userDetails.getId();
        this.userName = userName;
        this.userDetails = userDetails;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", billingInfo=" + userDetails +
                '}';
    }
}
