package cases.onetomany.unidirectional.regular;

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

    public LeaseContract() {
    }

    public LeaseContract(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
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
                '}';
    }
}
