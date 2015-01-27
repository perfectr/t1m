package nz.govt.doc.t1m.domain.dataSheets.incidentalBird;

import nz.govt.doc.t1m.domain.dataSheets.DataSheetEntity;

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

    public IncidentalBirdEntity(Integer surveyId) {
        this.surveyId = surveyId;
    }

    public Integer getDataSheetId() {
        return dataSheetId;
    }

    public Integer getSurveyId() {
        return surveyId;
    }
}
