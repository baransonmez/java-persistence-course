package cases.onetomany.unidirectional.regular;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;
    @Column(name = "vehicle_model")
    private String model;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LeaseContract> contractHistory = new ArrayList<>();

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
    public ArrayList<LeaseContract> getContractHistory() {
        return new ArrayList<>(contractHistory);
    }

    public void addContractToHistory(LeaseContract contract) {
        this.contractHistory.add(contract);
    }

    public void removeContractHistoryWithIndex(int index) {
        this.contractHistory.remove(index);
    }
}
