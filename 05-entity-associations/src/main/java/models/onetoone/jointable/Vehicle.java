package models.onetoone.jointable;

import jakarta.persistence.*;

@Entity(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "model", nullable = false)
    private String model;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "current_owner", joinColumns = @JoinColumn(name = "vehicle_id"), inverseJoinColumns = @JoinColumn(name = "user_id", nullable = false, unique = true))
    private User currentOwner;

    public Vehicle() {
    }

    public Vehicle(String model, User currentOwner) {
        this.model = model;
        this.currentOwner = currentOwner;
    }

    public Vehicle(String model) {
        this.model = model;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", model='" + model + '\'' +
                '}';
    }
}
