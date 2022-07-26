package cases.manytoone.unidirectional;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "leaseContracts")
public class LeaseContract {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime startDate;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "VEHICLE_ID", nullable = false)
    private Vehicle vehicle;

    public LeaseContract() {
    }

    public LeaseContract(LocalDateTime startDate, LocalDateTime endDate, Vehicle vehicle) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.vehicle = vehicle;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "LeaseContract{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", vehicle=" + vehicle +
                '}';
    }
}
