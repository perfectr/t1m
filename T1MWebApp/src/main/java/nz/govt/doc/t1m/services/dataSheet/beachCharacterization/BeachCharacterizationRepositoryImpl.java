package nz.govt.doc.t1m.services.dataSheet.beachCharacterization;

import com.mysema.query.jpa.impl.JPAQuery;
import nz.govt.doc.t1m.domain.dataSheet.beachCharacterization.BeachCharacterizationEntity;
import nz.govt.doc.t1m.domain.dataSheet.beachCharacterization.QBeachCharacterizationEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.services.dataSheet.beachCharacterization.BeachCharacterizationCriteria;
import nz.govt.doc.t1m.services.dataSheet.beachCharacterization.BeachCharacterizationRepositoryCustom;
import nz.govt.doc.t1m.utils.JPAUtils;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This is an example of how to use QueryDSL to create dynamic queries at runtime when the number of input parameters
 * can vary for each request.
 */
@Component
public class BeachCharacterizationRepositoryImpl implements BeachCharacterizationRepositoryCustom {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public PagedResponse<BeachCharacterizationEntity> findByCriteria(BeachCharacterizationCriteria criteria) {

//        String dataSheetId = JPAUtils.appendWildcard(getString(criteria.getDataSheetId()));
//        String surveyId = JPAUtils.appendWildcard(getString(criteria.getSurveyId()));
        String beachCharacterizationCriteria = JPAUtils.appendWildcard(criteria.getBeachCharacterizationCriteria());

        QBeachCharacterizationEntity beachCharacterizationEntity = QBeachCharacterizationEntity.beachCharacterizationEntity;
        JPAQuery query = new JPAQuery(em).from(beachCharacterizationEntity);

//        if(dataSheetId != null) {
//            query.where(beachCharacterizationEntity.dataSheetId.like(dataSheetId));
//        }
//
//        if(surveyId != null) {
//            query.where(beachCharacterizationEntity.surveyId.like(surveyId));
//        }

        if(beachCharacterizationCriteria != null) {
            query.where(beachCharacterizationEntity.dataSheetId.like(beachCharacterizationCriteria).or(beachCharacterizationEntity.surveyId.like(beachCharacterizationCriteria)));
        }

        query.orderBy(beachCharacterizationEntity.dataSheetId.desc());

        PagedResponse<BeachCharacterizationEntity> response = JPAUtils.listResults(query, criteria, beachCharacterizationEntity);
        return response;
    }

    private String getString(Integer id) {
        try {
            return id.toString();
        } catch (NullPointerException n) {}
        return new String();
    }
}
