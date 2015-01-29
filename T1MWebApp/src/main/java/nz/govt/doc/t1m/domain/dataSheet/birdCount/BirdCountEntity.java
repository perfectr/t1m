package nz.govt.doc.t1m.domain.dataSheet.birdCount;

import nz.govt.doc.t1m.domain.dataSheet.DataSheetEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by McCaulC on 27/01/2015.
 */
@Entity
public class BirdCountEntity extends DataSheetEntity {

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
}
