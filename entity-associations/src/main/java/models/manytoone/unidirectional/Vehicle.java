package models.manytoone.unidirectional;

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
                '}';
    }
}
