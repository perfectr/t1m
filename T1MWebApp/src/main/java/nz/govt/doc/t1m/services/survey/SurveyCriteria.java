package nz.govt.doc.t1m.services.survey;

import nz.govt.doc.t1m.domain.criteria.AbstractCriteria;

import java.util.Date;

/**
 */
public class SurveyCriteria extends AbstractCriteria {

    private String observer;
    private String locationId;
    private Date startD;
    private Date endD;
    private Date receivedD;

    private String surveyCriteria;

    public SurveyCriteria() {
        super();
    }

    public String getObserver() {
        return observer;
    }

    public void setObserver(String observer) {
        this.observer = observer;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public Date getStartD() {
        return startD;
    }

    public void setStartD(Date startD) {
        this.startD = startD;
    }

    public Date getEndD() {
        return endD;
    }

    public void setEndD(Date endD) {
        this.endD = endD;
    }

    public Date getReceivedD() {
        return receivedD;
    }

    public void setReceivedD(Date receivedD) {
        this.receivedD = receivedD;
    }

    public String getSurveyCriteria() {
        return surveyCriteria;
    }

    public void setSurveyCriteria(String surveyCriteria) {
        this.surveyCriteria = surveyCriteria;
    }
}
