package nz.govt.doc.t1m.domain.survey;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 */
@Entity
public class SurveyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer surveyId;
    @NotNull
    private String observer;
    @NotNull
    private String locationId;
    @NotNull
    private Date startD;
    @NotNull
    private Date endD;
    @NotNull
    private Date receivedD;
    @NotNull
    private String surveyType;

    public Integer getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Integer surveyId) {
        this.surveyId = surveyId;
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

    public String getStartDString() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(startD);
    }

    public void setStartD(Date startD) {
        this.startD = startD;
    }

    public Date getEndD() {
        return endD;
    }

    public String getEndDString() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(endD);
    }

    public void setEndD(Date endD) {
        this.endD = endD;
    }

    public Date getReceivedD() {
        return receivedD;
    }

    public String getReceivedDString() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy @ hh:mm:ss");
        return df.format(receivedD);
    }

    public void setReceivedD() {
        this.receivedD = new Timestamp(Calendar.getInstance().getTime().getTime());
    }

    public String getSurveyType() {
        return surveyType;
    }

    public void setSurveyType(String surveyType) {
        this.surveyType = surveyType;
    }
}
