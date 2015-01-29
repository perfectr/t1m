package nz.govt.doc.t1m.services.dataSheet.birdCount;

import nz.govt.doc.t1m.domain.criteria.AbstractCriteria;

/**
 * Created by McCaulC on 28/01/2015.
 */
public class BirdCountCriteria extends AbstractCriteria {

    private String birdCountCriteria;

    public BirdCountCriteria() {
        super();
    }

    public String getBirdCountCriteria() {
        return birdCountCriteria;
    }

    public void setBirdCountCriteria(String birdCountCriteria) {
        this.birdCountCriteria = birdCountCriteria;
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

    private String stationId;
    private String startTime;
    private String stationSkipped;
    private String reasonSkipped;
    private String sun;
    private String temp;
    private String precipitation;
    private String wind;
    private String otherNoise;
    private String easting;
    private String northing;
    private String elevation;
    private String position;
    private String notes;

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStationSkipped() {
        return stationSkipped;
    }

    public void setStationSkipped(String stationSkipped) {
        this.stationSkipped = stationSkipped;
    }

    public String getReasonSkipped() {
        return reasonSkipped;
    }

    public void setReasonSkipped(String reasonSkipped) {
        this.reasonSkipped = reasonSkipped;
    }

    public String getSun() {
        return sun;
    }

    public void setSun(String sun) {
        this.sun = sun;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getOtherNoise() {
        return otherNoise;
    }

    public void setOtherNoise(String otherNoise) {
        this.otherNoise = otherNoise;
    }

    public String getEasting() {
        return easting;
    }

    public void setEasting(String easting) {
        this.easting = easting;
    }

    public String getNorthing() {
        return northing;
    }

    public void setNorthing(String northing) {
        this.northing = northing;
    }

    public String getElevation() {
        return elevation;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
