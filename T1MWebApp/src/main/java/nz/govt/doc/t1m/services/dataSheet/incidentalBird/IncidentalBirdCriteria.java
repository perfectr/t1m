package nz.govt.doc.t1m.services.dataSheet.incidentalBird;

import nz.govt.doc.t1m.domain.criteria.AbstractCriteria;

/**
 * Created by McCaulC on 28/01/2015.
 */
public class IncidentalBirdCriteria extends AbstractCriteria {

    private String incidentalBirdCriteria;

    public IncidentalBirdCriteria() {
        super();
    }

    public String getIncidentalBirdCriteria() {
        return incidentalBirdCriteria;
    }

    public void setIncidentalBirdCriteria(String incidentalBirdCriteria) {
        this.incidentalBirdCriteria = incidentalBirdCriteria;
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

    private String species;
    private String numberObserved;
    private String notes;

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getNumberObserved() {
        return numberObserved;
    }

    public void setNumberObserved(String numberObserved) {
        this.numberObserved = numberObserved;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
