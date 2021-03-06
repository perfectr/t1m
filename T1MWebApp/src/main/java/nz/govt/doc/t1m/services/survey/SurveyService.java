package nz.govt.doc.t1m.services.survey;

import nz.govt.doc.t1m.domain.survey.SurveyEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.domain.response.Response;
import nz.govt.doc.t1m.services.incoming.DataForm;
import nz.govt.doc.t1m.services.incoming.DataParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 */
@Component
public class  SurveyService {

    @Autowired
    protected SurveyRepository surveyRepository;

    @Autowired
    protected DataParser dataParser;

    public PagedResponse<SurveyEntity> findByCriteria(SurveyCriteria criteria) {
        return surveyRepository.findByCriteria(criteria);
    }

    public Response<SurveyEntity> findSurveyById(Integer surveyId) {
        return new Response<>(surveyRepository.findOne(surveyId));
    }

    @Transactional
    public Response<SurveyEntity> saveSurvey(DataForm dataForm) {
        dataParser.initialize(dataForm);
        SurveyEntity surveyResponse = surveyRepository.save(dataParser.getSurvey());
        dataParser.saveDataSheets(surveyResponse.getSurveyId());
        Response res = new Response<>(surveyResponse);
        return res;
    }

    @Transactional
    public Response<SurveyEntity> saveSurvey(SurveyEntity survey) {
        SurveyEntity surveyResponse = surveyRepository.save(survey);
        return new Response<>(surveyResponse);
    }


    public void removeSurvey(Integer surveyId) {
        surveyRepository.delete(surveyId);
    }
}
