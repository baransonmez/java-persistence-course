package cases.manytomany.intermediateentity;

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

    @OneToMany(mappedBy = "tag")
    protected Set<TaggedVehicle> vehicles = new HashSet<>();

    public Tag() {
    }

    public Tag(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public Set<TaggedVehicle> getVehicles() {
        return new HashSet<>(vehicles);
    }

    public void addVehicle(TaggedVehicle tag) {
        this.vehicles.add(tag);
    }
}
