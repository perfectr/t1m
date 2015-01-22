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

    private String eventCriteria;

    public EventCriteria() {
        super();
    }

    public String getObserver() {
        return observer;
    }

    public void setObserver(String eventId) {
        this.observer = observer;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String eventId) {
        this.locationId = locationId;
    }

    public Date getStartD() {
        return startD;
    }

    public void setStartD(Date eventId) {
        this.startD = startD;
    }

    public Date getEndD() {
        return endD;
    }

    public void setEndD(Date eventId) {
        this.endD = endD;
    }

    public String getEventCriteria() {
        return eventCriteria;
    }

    public void setEventCriteria(String eventCriteria) {
        this.eventCriteria = eventCriteria;
    }
}
