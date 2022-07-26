package cases.manytomany.simple;

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

    @ManyToMany(mappedBy = "vehicles")
    protected Set<Tag> tags = new HashSet<>();

    public Vehicle() {
    }

    public Vehicle(String model) {
        this.model = model;
    }

    public Long getId() {
        return id;
    }

    public Set<Tag> getTags() {
        return new HashSet<>(tags);
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }
}
