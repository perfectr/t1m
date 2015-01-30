package nz.govt.doc.t1m.services.instance.birdDistance;

import nz.govt.doc.t1m.domain.criteria.AbstractCriteria;

/**
 * Created by McCaulC on 28/01/2015.
 */
public class BDBirdCriteria extends AbstractCriteria {

    private String bdBirdCriteria;

    public BDBirdCriteria() {
        super();
    }

    public String getBDBirdCriteria() {
        return bdBirdCriteria;
    }

    public void setBDBirdCriteria(String bdBirdCriteria) {
        this.bdBirdCriteria = bdBirdCriteria;
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
    private String distance;
    private String clusterSize;
    private String clusterAccuracy;
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

    public String getClusterSize() {
        return clusterSize;
    }

    public void setClusterSize(String clusterSize) {
        this.clusterSize = clusterSize;
    }

    public String getClusterAccuracy() {
        return clusterAccuracy;
    }

    public void setClusterAccuracy(String clusterAccuracy) {
        this.clusterAccuracy = clusterAccuracy;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
