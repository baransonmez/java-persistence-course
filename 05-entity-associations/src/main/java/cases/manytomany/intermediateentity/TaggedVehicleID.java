package cases.manytomany.intermediateentity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TaggedVehicleID implements Serializable {
    @Column(name = "tag_id")
    private Long tagId;

    @Column(name = "vehicle_id")
    private Long vehicleId;

    public TaggedVehicleID() {
    }

    public TaggedVehicleID(Long tagId, Long vehicleId) {
        this.tagId = tagId;
        this.vehicleId = vehicleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaggedVehicleID that = (TaggedVehicleID) o;

        if (!Objects.equals(tagId, that.tagId)) return false;
        return Objects.equals(vehicleId, that.vehicleId);
    }

    @Override
    public int hashCode() {
        int result = tagId != null ? tagId.hashCode() : 0;
        result = 31 * result + (vehicleId != null ? vehicleId.hashCode() : 0);
        return result;
    }
}
