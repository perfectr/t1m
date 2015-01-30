package nz.govt.doc.t1m.services.dataSheet.litterBeach;

import com.mysema.query.jpa.impl.JPAQuery;
import nz.govt.doc.t1m.domain.dataSheet.litterBeach.LitterBeachEntity;
import nz.govt.doc.t1m.domain.dataSheet.litterBeach.QLitterBeachEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.services.dataSheet.litterBeach.LitterBeachCriteria;
import nz.govt.doc.t1m.services.dataSheet.litterBeach.LitterBeachRepositoryCustom;
import nz.govt.doc.t1m.utils.JPAUtils;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This is an example of how to use QueryDSL to create dynamic queries at runtime when the number of input parameters
 * can vary for each request.
 */
@Component
public class LitterBeachRepositoryImpl implements LitterBeachRepositoryCustom {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public PagedResponse<LitterBeachEntity> findByCriteria(LitterBeachCriteria criteria) {

//        String dataSheetId = JPAUtils.appendWildcard(getString(criteria.getDataSheetId()));
//        String surveyId = JPAUtils.appendWildcard(getString(criteria.getSurveyId()));
        String litterBeachCriteria = JPAUtils.appendWildcard(criteria.getLitterBeachCriteria());

        QLitterBeachEntity litterBeachEntity = QLitterBeachEntity.litterBeachEntity;
        JPAQuery query = new JPAQuery(em).from(litterBeachEntity);

//        if(dataSheetId != null) {
//            query.where(litterBeachEntity.dataSheetId.like(dataSheetId));
//        }
//
//        if(surveyId != null) {
//            query.where(litterBeachEntity.surveyId.like(surveyId));
//        }

        if(litterBeachCriteria != null) {
            query.where(litterBeachEntity.dataSheetId.like(litterBeachCriteria).or(litterBeachEntity.surveyId.like(litterBeachCriteria)));
        }

        query.orderBy(litterBeachEntity.dataSheetId.desc());

        PagedResponse<LitterBeachEntity> response = JPAUtils.listResults(query, criteria, litterBeachEntity);
        return response;
    }

    private String getString(Integer id) {
        try {
            return id.toString();
        } catch (NullPointerException n) {}
        return new String();
    }
}
