package nz.govt.doc.t1m.domain.dataSheet.litterBeach;

import nz.govt.doc.t1m.domain.dataSheet.DataSheetEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by McCaulC on 27/01/2015.
 */
@Entity
public class LitterBeachEntity extends DataSheetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dataSheetId;
    @NotNull
    private Integer surveyId;

    public void setSurveyId(Integer surveyId) {
        this.surveyId = surveyId;
    }

    public Integer getDataSheetId() {
        return dataSheetId;
    }

    public Integer getSurveyId() {
        return surveyId;
    }

    private String date;
    private String latitudeStart;
    private String longitudeStart;
    private String latitudeEnd;
    private String longitudeEnd;
    private String coordSystem;
    private String timeStart;
    private String timeEnd;
    private String season;
    private String stormActivity;
    private String beachLength;
    private String beachWidth;
    private String personsSurveying;
    private String notes;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLatitudeStart() {
        return latitudeStart;
    }

    public void setLatitudeStart(String latitudeStart) {
        this.latitudeStart = latitudeStart;
    }

    public String getLongitudeStart() {
        return longitudeStart;
    }

    public void setLongitudeStart(String longitudeStart) {
        this.longitudeStart = longitudeStart;
    }

    public String getLatitudeEnd() {
        return latitudeEnd;
    }

    public void setLatitudeEnd(String latitudeEnd) {
        this.latitudeEnd = latitudeEnd;
    }

    public String getLongitudeEnd() {
        return longitudeEnd;
    }

    public void setLongitudeEnd(String longitudeEnd) {
        this.longitudeEnd = longitudeEnd;
    }

    public String getCoordSystem() {
        return coordSystem;
    }

    public void setCoordSystem(String coordSystem) {
        this.coordSystem = coordSystem;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getStormActivity() {
        return stormActivity;
    }

    public void setStormActivity(String stormActivity) {
        this.stormActivity = stormActivity;
    }

    public String getBeachLength() {
        return beachLength;
    }

    public void setBeachLength(String beachLength) {
        this.beachLength = beachLength;
    }

    public String getBeachWidth() {
        return beachWidth;
    }

    public void setBeachWidth(String beachWidth) {
        this.beachWidth = beachWidth;
    }

    public String getPersonsSurveying() {
        return personsSurveying;
    }

    public void setPersonsSurveying(String personsSurveying) {
        this.personsSurveying = personsSurveying;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
