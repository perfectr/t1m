package nz.govt.doc.t1m.domain.instance.litterLarge;

import nz.govt.doc.t1m.domain.dataSheet.DataSheetEntity;
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
public class LLItemEntity extends InstanceEntity {

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
