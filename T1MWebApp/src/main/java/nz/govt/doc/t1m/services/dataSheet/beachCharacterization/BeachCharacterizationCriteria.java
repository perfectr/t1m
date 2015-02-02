package nz.govt.doc.t1m.services.dataSheet.beachCharacterization;

import nz.govt.doc.t1m.domain.criteria.AbstractCriteria;

/**
 * Created by McCaulC on 28/01/2015.
 */
public class BeachCharacterizationCriteria extends AbstractCriteria {

    private String beachCharacterizationCriteria;

    public BeachCharacterizationCriteria() {
        super();
    }

    public String getBeachCharacterizationCriteria() {
        return beachCharacterizationCriteria;
    }

    public void setBeachCharacterizationCriteria(String beachCharacterizationCriteria) {
        this.beachCharacterizationCriteria = beachCharacterizationCriteria;
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

    private String beachId;
    private String beachName;
    private String beachLength;
    private String regionName;
    private String latitudeStart;
    private String longitudeStart;
    private String latitudeEnd;
    private String longitudeEnd;
    private String coordSystem;
    private String substratumType;
    private String substratumUniformity;
    private String tidalRange;
    private String tidalDistance;
    private String beachBacking;
    private String location;
    private String majorUsage;
    private String access;
    private String nearestTown;
    private String nearestTownDistance;
    private String nearestTownDirection;
    private String nearestRiver;
    private String nearestRiverDistance;
    private String nearestRiverDirection;
    private String riversCreeks;
    private String pipesDrains;
    private String notes;

    public String getBeachId() {
        return beachId;
    }

    public void setBeachId(String beachId) {
        this.beachId = beachId;
    }

    public String getBeachName() {
        return beachName;
    }

    public void setBeachName(String beachName) {
        this.beachName = beachName;
    }

    public String getBeachLength() {
        return beachLength;
    }

    public void setBeachLength(String beachLength) {
        this.beachLength = beachLength;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
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

    public String getSubstratumType() {
        return substratumType;
    }

    public void setSubstratumType(String substratumType) {
        this.substratumType = substratumType;
    }

    public String getSubstratumUniformity() {
        return substratumUniformity;
    }

    public void setSubstratumUniformity(String substratumUniformity) {
        this.substratumUniformity = substratumUniformity;
    }

    public String getTidalRange() {
        return tidalRange;
    }

    public void setTidalRange(String tidalRange) {
        this.tidalRange = tidalRange;
    }

    public String getTidalDistance() {
        return tidalDistance;
    }

    public void setTidalDistance(String tidalDistance) {
        this.tidalDistance = tidalDistance;
    }

    public String getBeachBacking() {
        return beachBacking;
    }

    public void setBeachBacking(String beachBacking) {
        this.beachBacking = beachBacking;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMajorUsage() {
        return majorUsage;
    }

    public void setMajorUsage(String majorUsage) {
        this.majorUsage = majorUsage;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getNearestTown() {
        return nearestTown;
    }

    public void setNearestTown(String nearestTown) {
        this.nearestTown = nearestTown;
    }

    public String getNearestTownDistance() {
        return nearestTownDistance;
    }

    public void setNearestTownDistance(String nearestTownDistance) {
        this.nearestTownDistance = nearestTownDistance;
    }

    public String getNearestTownDirection() {
        return nearestTownDirection;
    }

    public void setNearestTownDirection(String nearestTownDirection) {
        this.nearestTownDirection = nearestTownDirection;
    }

    public String getNearestRiver() {
        return nearestRiver;
    }

    public void setNearestRiver(String nearestRiver) {
        this.nearestRiver = nearestRiver;
    }

    public String getNearestRiverDistance() {
        return nearestRiverDistance;
    }

    public void setNearestRiverDistance(String nearestRiverDistance) {
        this.nearestRiverDistance = nearestRiverDistance;
    }

    public String getNearestRiverDirection() {
        return nearestRiverDirection;
    }

    public void setNearestRiverDirection(String nearestRiverDirection) {
        this.nearestRiverDirection = nearestRiverDirection;
    }

    public String getRiversCreeks() {
        return riversCreeks;
    }

    public void setRiversCreeks(String riversCreeks) {
        this.riversCreeks = riversCreeks;
    }

    public String getPipesDrains() {
        return pipesDrains;
    }

    public void setPipesDrains(String pipesDrains) {
        this.pipesDrains = pipesDrains;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
