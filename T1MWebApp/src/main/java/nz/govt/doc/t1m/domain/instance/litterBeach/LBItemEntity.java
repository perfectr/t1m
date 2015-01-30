package nz.govt.doc.t1m.domain.instance.litterBeach;

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
public class LBItemEntity extends InstanceEntity {

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
    private String count;
    private String weight;
    private String notes;

    public String getLitterCode() {
        return litterCode;
    }

    public void setLitterCode(String litterCode) {
        this.litterCode = litterCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
