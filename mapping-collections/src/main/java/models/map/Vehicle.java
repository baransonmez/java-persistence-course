package models.map;

import jakarta.persistence.*;
import models.Condition;
import models.VehicleType;

import java.util.HashMap;
import java.util.Map;

@Entity(name = "vehicleWithMap")
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;
    @Column(name = "vehicle_model")
    private String model;

    private double dailyPrice;
    @Column(name = "model_year")
    private int year;

    @Column(name = "vehicle_type")
    @Enumerated(EnumType.ORDINAL)
    private VehicleType type;
    @Column(name = "vehicle_condition")
    @Enumerated(EnumType.STRING)
    private Condition condition;

    @ElementCollection
    @CollectionTable(name = "IMAGE_MAP")
    @Column(name = "IMAGE_PATH")
    @MapKeyColumn(name = "FILENAME")
    private Map<String, String> images = new HashMap<>();

    public Vehicle(String model, int year, double dailyPrice, VehicleType type, Condition condition, Map<String, String> images) {
        this.model = model;
        this.year = year;
        this.dailyPrice = dailyPrice;
        this.type = type;
        this.condition = condition;
        this.images = images;
    }

    public Vehicle() {
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", dailyPrice=" + dailyPrice +
                ", year=" + year +
                ", type=" + type +
                ", condition=" + condition +
                ", images=" + images +
                '}';
    }

}
