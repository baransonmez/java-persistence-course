package cases.onetoone.jointable;

import jakarta.persistence.*;

@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @OneToOne(mappedBy = "currentOwner")
    @JoinColumn
    private Vehicle currentVehicle;

    public User() {
    }

    public User(String userName) {
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", vehicle=" + currentVehicle +
                '}';
    }
}
