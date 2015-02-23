package nz.govt.doc.t1m.domain.instance.birdCount;

import nz.govt.doc.t1m.domain.instance.InstanceEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * table for storing data on bird count bird instances
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
    private String numNear;
    private String numFar;
    private String numVFar;
    private String notes;

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getNumNear() {
        return numNear;
    }

    public void setNumNear(String numNear) {
        this.numNear = numNear;
    }

    public String getNumFar() {
        return numFar;
    }

    public void setNumFar(String numFar) {
        this.numFar = numFar;
    }

    public String getNumVFar() {
        return numVFar;
    }

    public void setNumVFar(String numVFar) {
        this.numVFar = numVFar;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getTotal() {
        try {
            return Integer.getInteger(numNear) + Integer.getInteger(numFar) + Integer.getInteger(numVFar);
        } catch (Exception e) {
            return -1;
        }
    }
}
