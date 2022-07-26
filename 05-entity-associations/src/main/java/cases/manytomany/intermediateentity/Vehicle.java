package cases.manytomany.intermediateentity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;
    @Column(name = "vehicle_model")
    private String model;

    @OneToMany(mappedBy = "vehicle")
    protected Set<TaggedVehicle> tags = new HashSet<>();

    public Vehicle() {
    }

    public Vehicle(String model) {
        this.model = model;
    }

    public Long getId() {
        return id;
    }

    public Set<TaggedVehicle> getTags() {
        return new HashSet<>(tags);
    }

    public void addTag(TaggedVehicle tag) {
        this.tags.add(tag);
    }

}
