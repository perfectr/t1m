package nz.govt.doc.t1m.services.instance.birdCount;

import nz.govt.doc.t1m.domain.criteria.AbstractCriteria;

/**
 * Created by McCaulC on 28/01/2015.
 */
public class BCBirdCriteria extends AbstractCriteria {

    private String bcBirdCriteria;

    public BCBirdCriteria() {
        super();
    }

    public String getBCBirdCriteria() {
        return bcBirdCriteria;
    }

    public void setBCBirdCriteria(String bcBirdCriteria) {
        this.bcBirdCriteria = bcBirdCriteria;
    }

    private Integer instanceId;
    private Integer dataSheetId;

    public Integer getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(Integer instanceId) {
        this.instanceId = instanceId;
    }

    public Integer getDataSheetId() {
        return dataSheetId;
    }

    public void setDataSheetId(Integer dataSheetId) {
        this.dataSheetId = dataSheetId;
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
}
