package nz.govt.doc.t1m.services.dataSheet;

import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.domain.response.Response;
import nz.govt.doc.t1m.domain.survey.SurveyEntity;
import nz.govt.doc.t1m.services.incoming.DataParser;
import nz.govt.doc.t1m.services.survey.SurveyCriteria;
import nz.govt.doc.t1m.services.survey.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 */
@Component
public class DataSheetService {

    @Autowired
    protected SurveyRepository surveyRepository;

    public PagedResponse<SurveyEntity> findByCriteria(SurveyCriteria criteria) {
        return surveyRepository.findByCriteria(criteria);
    }

    public Response<SurveyEntity> findSurveyById(Integer surveyId) {
        return new Response<>(surveyRepository.findOne(surveyId));
    }

    @Transactional
    public Response<SurveyEntity> saveSurvey(DataParser dataParser) {
        SurveyEntity surveyResponse = surveyRepository.save(dataParser.getSurvey());
        dataParser.saveDataSheets(surveyResponse.getSurveyId());
        Response res = new Response<>(surveyResponse);
        return res;
    }

    public void removeSurvey(Integer surveyId) {
        surveyRepository.delete(surveyId);
    }
}
