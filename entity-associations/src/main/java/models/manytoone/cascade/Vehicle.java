package models.manytoone.cascade;

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

    @OneToMany(mappedBy = "vehicle", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
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
    /*
    Returning the current collection in getters is not suitable for software practice.
    Because where we use Getter, we don't actually plan to make any changes, but only to read.
    If we made it open to change after this get() function, we would not be sure where this field has changed.
    We return a copy of the current collection inside get() to ensure that the get function is only used for the read operation.
    */
    public Set<LeaseContract> getContractHistory() {
        return new HashSet<>(contractHistory);
    }

    public void addContractToHistory(LeaseContract contract) {
        this.contractHistory.add(contract);
    }
}
