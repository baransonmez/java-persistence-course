package models.manytoone.bidirectional;

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

    @OneToMany(mappedBy = "vehicle", fetch = FetchType.EAGER)
    private Set<LeaseContract> contractHistory = new HashSet<>();

    public Vehicle() {
    }

    public Vehicle(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", contractHistory=" + contractHistory +
                '}';
    }

    public Long getId() {
        return id;
    }

    public Set<LeaseContract> getContractHistory() {
        return contractHistory;
    }
}
