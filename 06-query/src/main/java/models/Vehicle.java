package models;

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
    @Column(name = "FILENAME")
    @CollectionTable(name = "IMAGE_SET", joinColumns = @JoinColumn(name = "VEHICLE_ID"))
    private Set<String> images = new HashSet<>();

    public Vehicle(String model, int year, double dailyPrice, VehicleType type, Condition condition, Set<String> images) {
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

    public Long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public double getDailyPrice() {
        return dailyPrice;
    }

    public int getYear() {
        return year;
    }

    public VehicleType getType() {
        return type;
    }

    public Condition getCondition() {
        return condition;
    }

    public Set<String> getImages() {
        return new HashSet<>(images);
    }
}
