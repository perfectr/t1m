package nz.govt.doc.t1m.services.dataSheet.incidentalBird;

import com.mysema.query.jpa.impl.JPAQuery;
import nz.govt.doc.t1m.domain.dataSheet.incidentalBird.IncidentalBirdEntity;
import nz.govt.doc.t1m.domain.dataSheet.incidentalBird.QIncidentalBirdEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.services.dataSheet.incidentalBird.IncidentalBirdCriteria;
import nz.govt.doc.t1m.services.dataSheet.incidentalBird.IncidentalBirdRepositoryCustom;
import nz.govt.doc.t1m.utils.JPAUtils;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This is an example of how to use QueryDSL to create dynamic queries at runtime when the number of input parameters
 * can vary for each request.
 */
@Component
public class IncidentalBirdRepositoryImpl implements IncidentalBirdRepositoryCustom {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public PagedResponse<IncidentalBirdEntity> findByCriteria(IncidentalBirdCriteria criteria) {

//        String dataSheetId = JPAUtils.appendWildcard(getString(criteria.getDataSheetId()));
//        String surveyId = JPAUtils.appendWildcard(getString(criteria.getSurveyId()));
        String incidentalBirdCriteria = JPAUtils.appendWildcard(criteria.getIncidentalBirdCriteria());

        QIncidentalBirdEntity incidentalBirdEntity = QIncidentalBirdEntity.incidentalBirdEntity;
        JPAQuery query = new JPAQuery(em).from(incidentalBirdEntity);

//        if(dataSheetId != null) {
//            query.where(incidentalBirdEntity.dataSheetId.like(dataSheetId));
//        }
//
//        if(surveyId != null) {
//            query.where(incidentalBirdEntity.surveyId.like(surveyId));
//        }

        if(incidentalBirdCriteria != null) {
            query.where(incidentalBirdEntity.dataSheetId.like(incidentalBirdCriteria).or(incidentalBirdEntity.surveyId.like(incidentalBirdCriteria)));
        }

        query.orderBy(incidentalBirdEntity.dataSheetId.desc());

        PagedResponse<IncidentalBirdEntity> response = JPAUtils.listResults(query, criteria, incidentalBirdEntity);
        return response;
    }

    private String getString(Integer id) {
        try {
            return id.toString();
        } catch (NullPointerException n) {}
        return new String();
    }
}
