package nz.govt.doc.t1m.services.dataSheet.litterLarge;

import nz.govt.doc.t1m.domain.criteria.AbstractCriteria;

/**
 * Created by McCaulC on 28/01/2015.
 */
public class LitterLargeCriteria extends AbstractCriteria {

    private String litterLargeCriteria;

    public LitterLargeCriteria() {
        super();
    }

    public String getLitterLargeCriteria() {
        return litterLargeCriteria;
    }

    public void setLitterLargeCriteria(String litterLargeCriteria) {
        this.litterLargeCriteria = litterLargeCriteria;
    }

    private Integer dataSheetId;
    private Integer surveyId;

    public Integer getDataSheetId() {
        return dataSheetId;
    }

    public void setDataSheetId(Integer dataSheetId) {
        this.dataSheetId = dataSheetId;
    }

    public Integer getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Integer surveyId) {
        this.surveyId = surveyId;
    }

    private String lastSurveyDate;
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

    public String getLastSurveyDate() {
        return lastSurveyDate;
    }

    public void setLastSurveyDate(String lastSurveyDate) {
        this.lastSurveyDate = lastSurveyDate;
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

    public String getLargeLength() {
        return beachLength;
    }

    public void setLargeLength(String beachLength) {
        this.beachLength = beachLength;
    }

    public String getLargeWidth() {
        return beachWidth;
    }

    public void setLargeWidth(String beachWidth) {
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