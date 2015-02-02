package nz.govt.doc.t1m.services.instance.litterBeach;

import nz.govt.doc.t1m.domain.criteria.AbstractCriteria;

/**
 * Created by McCaulC on 28/01/2015.
 */
public class LBItemCriteria extends AbstractCriteria {

    private String lbItemCriteria;

    public LBItemCriteria() {
        super();
    }

    public String getLBItemCriteria() {
        return lbItemCriteria;
    }

    public void setLBItemCriteria(String lbItemCriteria) {
        this.lbItemCriteria = lbItemCriteria;
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
