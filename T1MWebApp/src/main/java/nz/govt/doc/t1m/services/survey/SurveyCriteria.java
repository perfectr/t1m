package nz.govt.doc.t1m.services.survey;

import nz.govt.doc.t1m.domain.criteria.AbstractCriteria;

import java.util.Date;

/**
 */
public class SurveyCriteria extends AbstractCriteria {

    private String surveyId;
    private String observer;
    private String locationId;
    private String surveyType;

    private String surveyCriteria;

    public SurveyCriteria() {
        super();
    }

    public String getSurveyId() {
        return surveyId;
    }

    public String getObserver() {
        return observer;
    }

    public String getLocationId() {
        return locationId;
    }

    public String getSurveyType() {
        return surveyType;
    }

    public String getSurveyCriteria() {
        return surveyCriteria;
    }
}
