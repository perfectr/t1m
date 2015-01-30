package nz.govt.doc.t1m.domain.instance.birdCount;

import nz.govt.doc.t1m.domain.instance.InstanceEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by McCaulC on 27/01/2015.
 */
@Entity
public class BCBirdEntity extends InstanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer instanceId;
    @NotNull
    private Integer dataSheetId;

    public void setDataSheetId(Integer dataSheetId) {
        this.dataSheetId = dataSheetId;
    }

    public Integer getInstanceId() {
        return instanceId;
    }

    public Integer getDataSheetId() {
        return dataSheetId;
    }

    private String species;
    private String distance;
    private String notes;

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
