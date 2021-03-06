package nz.govt.doc.t1m.services.survey;

import com.mysema.query.jpa.impl.JPAQuery;
import nz.govt.doc.t1m.domain.survey.SurveyEntity;
import nz.govt.doc.t1m.domain.survey.QSurveyEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.utils.JPAUtils;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This is an example of how to use QueryDSL to create dynamic queries at runtime when the number of input parameters
 * can vary for each request.
 */
@Component
public class SurveyRepositoryImpl implements SurveyRepositoryCustom {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public PagedResponse<SurveyEntity> findByCriteria(SurveyCriteria criteria) {
        String surveyCriteria = JPAUtils.appendWildcard(criteria.getSurveyCriteria());

        QSurveyEntity surveyEntity = QSurveyEntity.surveyEntity;
        JPAQuery query = new JPAQuery(em).from(surveyEntity);

        if(surveyCriteria != null) {
            query.where(
                    surveyEntity.surveyId.like(surveyCriteria).or(
                            surveyEntity.observer.like(surveyCriteria).or(
                                    surveyEntity.locationId.like(surveyCriteria).or(
                                            surveyEntity.surveyType.like(surveyCriteria)))));
        }

        query.orderBy(surveyEntity.receivedD.desc());

        PagedResponse<SurveyEntity> response = JPAUtils.listResults(query, criteria, surveyEntity);
        return response;
    }

}
