package nz.govt.doc.t1m.domain.dataSheet.incidentalBird;

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
public class IncidentalBirdEntity extends DataSheetEntity {

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
