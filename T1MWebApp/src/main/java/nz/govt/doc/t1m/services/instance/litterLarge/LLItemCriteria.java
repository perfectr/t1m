package nz.govt.doc.t1m.services.instance.litterLarge;

import nz.govt.doc.t1m.domain.criteria.AbstractCriteria;

/**
 * Created by McCaulC on 28/01/2015.
 */
public class LLItemCriteria extends AbstractCriteria {

    private String llItemCriteria;

    public LLItemCriteria() {
        super();
    }

    public String getLLItemCriteria() {
        return llItemCriteria;
    }

    public void setLLItemCriteria(String llItemCriteria) {
        this.llItemCriteria = llItemCriteria;
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

    private String litterCode;
    private String description;
    private String status;
    private String latitude;
    private String longitude;
    private String notes;

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLitterCode() {
        return litterCode;
    }

    public void setLitterCode(String litterCode) {
        this.litterCode = litterCode;
    }
}
