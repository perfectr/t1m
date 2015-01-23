package nz.govt.doc.t1m.services.event;

import nz.govt.doc.t1m.domain.criteria.AbstractCriteria;

import java.util.Date;

/**
 */
public class EventCriteria extends AbstractCriteria {

    private String observer;
    private String locationId;
    private Date startD;
    private Date endD;
    private Date receivedD;

    private String eventCriteria;

    public EventCriteria() {
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

    public String getEventCriteria() {
        return eventCriteria;
    }

    public void setEventCriteria(String eventCriteria) {
        this.eventCriteria = eventCriteria;
    }
}
