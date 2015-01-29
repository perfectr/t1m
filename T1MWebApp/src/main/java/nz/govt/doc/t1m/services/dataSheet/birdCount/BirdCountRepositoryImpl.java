package nz.govt.doc.t1m.services.dataSheet.birdCount;

import com.mysema.query.jpa.impl.JPAQuery;
import nz.govt.doc.t1m.domain.dataSheet.birdCount.BirdCountEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.domain.dataSheet.birdCount.QBirdCountEntity;
import nz.govt.doc.t1m.utils.JPAUtils;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This is an example of how to use QueryDSL to create dynamic queries at runtime when the number of input parameters
 * can vary for each request.
 */
@Component
public class BirdCountRepositoryImpl implements BirdCountRepositoryCustom {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public PagedResponse<BirdCountEntity> findByCriteria(BirdCountCriteria criteria) {

//        String dataSheetId = JPAUtils.appendWildcard(getString(criteria.getDataSheetId()));
//        String surveyId = JPAUtils.appendWildcard(getString(criteria.getSurveyId()));
        String birdCountCriteria = JPAUtils.appendWildcard(criteria.getBirdCountCriteria());

        QBirdCountEntity birdCountEntity = QBirdCountEntity.birdCountEntity;
        JPAQuery query = new JPAQuery(em).from(birdCountEntity);

//        if(dataSheetId != null) {
//            query.where(birdCountEntity.dataSheetId.like(dataSheetId));
//            System.out.println("x");
//        }
//
//        if(surveyId != null) {
//            query.where(birdCountEntity.surveyId.like(surveyId));
//            System.out.println("y");
//        }

        if(birdCountCriteria != null) {
            query.where(birdCountEntity.dataSheetId.like(birdCountCriteria).or(birdCountEntity.surveyId.like(birdCountCriteria)));
            System.out.println("z");
        }

        query.orderBy(birdCountEntity.dataSheetId.desc());

        PagedResponse<BirdCountEntity> response = JPAUtils.listResults(query, criteria, birdCountEntity);
        return response;
    }

    private String getString(Integer id) {
        try {
            return id.toString();
        } catch (java.lang.NullPointerException n) {}
        return new String();
    }
}
