package cases.manytomany.simple;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;
    @Column(name = "description")
    private String description;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "TAG_VEHICLE",
            joinColumns = @JoinColumn(name = "TAG_ID"),
            inverseJoinColumns = @JoinColumn(name = "VEHICLE_ID")
    )
    protected Set<Vehicle> vehicles = new HashSet<>();

    public Tag() {
    }

    public Tag(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public Set<Vehicle> getVehicles() {
        return new HashSet<>(vehicles);
    }

    public void addVehicle(Vehicle vehicle) {
        if (vehicle != null) {
            vehicle.addTag(this);
        }
        this.vehicles.add(vehicle);
    }
}
