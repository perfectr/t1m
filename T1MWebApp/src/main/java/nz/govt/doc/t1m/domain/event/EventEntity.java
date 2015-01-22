package nz.govt.doc.t1m.domain.event;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 */
@Entity
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eventId;
    @NotNull
    private String observer;
    @NotNull
    private String locationId;
    private Date startD;
    private Date endD;

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
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



}
