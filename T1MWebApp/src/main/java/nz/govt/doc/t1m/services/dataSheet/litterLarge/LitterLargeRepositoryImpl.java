package nz.govt.doc.t1m.services.dataSheet.litterLarge;

import com.mysema.query.jpa.impl.JPAQuery;
import nz.govt.doc.t1m.domain.dataSheet.litterLarge.LitterLargeEntity;
import nz.govt.doc.t1m.domain.dataSheet.litterLarge.QLitterLargeEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.services.dataSheet.litterLarge.LitterLargeCriteria;
import nz.govt.doc.t1m.services.dataSheet.litterLarge.LitterLargeRepositoryCustom;
import nz.govt.doc.t1m.utils.JPAUtils;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This is an example of how to use QueryDSL to create dynamic queries at runtime when the number of input parameters
 * can vary for each request.
 */
@Component
public class LitterLargeRepositoryImpl implements LitterLargeRepositoryCustom {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public PagedResponse<LitterLargeEntity> findByCriteria(LitterLargeCriteria criteria) {

//        String dataSheetId = JPAUtils.appendWildcard(getString(criteria.getDataSheetId()));
//        String surveyId = JPAUtils.appendWildcard(getString(criteria.getSurveyId()));
        String litterLargeCriteria = JPAUtils.appendWildcard(criteria.getLitterLargeCriteria());

        QLitterLargeEntity litterLargeEntity = QLitterLargeEntity.litterLargeEntity;
        JPAQuery query = new JPAQuery(em).from(litterLargeEntity);

//        if(dataSheetId != null) {
//            query.where(litterLargeEntity.dataSheetId.like(dataSheetId));
//        }
//
//        if(surveyId != null) {
//            query.where(litterLargeEntity.surveyId.like(surveyId));
//        }

        if(litterLargeCriteria != null) {
            query.where(litterLargeEntity.dataSheetId.like(litterLargeCriteria).or(litterLargeEntity.surveyId.like(litterLargeCriteria)));
        }

        query.orderBy(litterLargeEntity.dataSheetId.desc());

        PagedResponse<LitterLargeEntity> response = JPAUtils.listResults(query, criteria, litterLargeEntity);
        return response;
    }

    private String getString(Integer id) {
        try {
            return id.toString();
        } catch (NullPointerException n) {}
        return new String();
    }
}
