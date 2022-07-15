package models.manytomany.intermediateentity;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

import java.util.Date;

@Entity
@Table(name = "tagged_vehicle")
@Immutable
public class TaggedVehicle {

    @EmbeddedId
    private TaggedVehicleID id;

    private Date createdOn;
    private String createdBy;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", insertable = false, updatable = false)
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "tag_id", insertable = false, updatable = false)
    private Tag tag;

    public TaggedVehicle() {
    }

    public TaggedVehicle(String createdBy, Vehicle vehicle, Tag tag) {
        this.createdBy = createdBy;
        this.vehicle = vehicle;
        this.tag = tag;
        this.createdOn = new Date();

        this.id = new TaggedVehicleID(tag.getId(), vehicle.getId());
    }
}
