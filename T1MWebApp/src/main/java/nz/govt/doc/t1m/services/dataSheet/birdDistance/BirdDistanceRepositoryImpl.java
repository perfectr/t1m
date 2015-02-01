package nz.govt.doc.t1m.services.dataSheet.birdDistance;

import com.mysema.query.jpa.impl.JPAQuery;
import nz.govt.doc.t1m.domain.dataSheet.birdDistance.BirdDistanceEntity;
import nz.govt.doc.t1m.domain.dataSheet.birdDistance.QBirdDistanceEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.services.dataSheet.birdDistance.BirdDistanceCriteria;
import nz.govt.doc.t1m.services.dataSheet.birdDistance.BirdDistanceRepositoryCustom;
import nz.govt.doc.t1m.utils.JPAUtils;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This is an example of how to use QueryDSL to create dynamic queries at runtime when the number of input parameters
 * can vary for each request.
 */
@Component
public class BirdDistanceRepositoryImpl implements BirdDistanceRepositoryCustom {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public PagedResponse<BirdDistanceEntity> findByCriteria(BirdDistanceCriteria criteria) {

//        String dataSheetId = JPAUtils.appendWildcard(getString(criteria.getDataSheetId()));
//        String surveyId = JPAUtils.appendWildcard(getString(criteria.getSurveyId()));
        String birdDistanceCriteria = JPAUtils.appendWildcard(criteria.getBirdDistanceCriteria());

        QBirdDistanceEntity birdDistanceEntity = QBirdDistanceEntity.birdDistanceEntity;
        JPAQuery query = new JPAQuery(em).from(birdDistanceEntity);

//        if(dataSheetId != null) {
//            query.where(birdDistanceEntity.dataSheetId.like(dataSheetId));
//        }
//
//        if(surveyId != null) {
//            query.where(birdDistanceEntity.surveyId.like(surveyId));
//        }

        if(birdDistanceCriteria != null) {
            query.where(birdDistanceEntity.dataSheetId.like(birdDistanceCriteria).or(birdDistanceEntity.surveyId.like(birdDistanceCriteria)));
        }

        query.orderBy(birdDistanceEntity.dataSheetId.desc());

        PagedResponse<BirdDistanceEntity> response = JPAUtils.listResults(query, criteria, birdDistanceEntity);
        return response;
    }

    private String getString(Integer id) {
        try {
            return id.toString();
        } catch (NullPointerException n) {}
        return new String();
    }
}
