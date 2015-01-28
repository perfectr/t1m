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
}
