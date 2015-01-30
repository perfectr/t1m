package nz.govt.doc.t1m.services.instance.birdCount;

import com.mysema.query.jpa.impl.JPAQuery;
import nz.govt.doc.t1m.domain.instance.birdCount.BCBirdEntity;
import nz.govt.doc.t1m.domain.instance.birdCount.QBCBirdEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.services.instance.birdCount.BCBirdCriteria;
import nz.govt.doc.t1m.services.instance.birdCount.BCBirdRepositoryCustom;
import nz.govt.doc.t1m.utils.JPAUtils;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This is an example of how to use QueryDSL to create dynamic queries at runtime when the number of input parameters
 * can vary for each request.
 */
@Component
public class BCBirdRepositoryImpl implements BCBirdRepositoryCustom {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public PagedResponse<BCBirdEntity> findByCriteria(BCBirdCriteria criteria) {

//        String instanceId = JPAUtils.appendWildcard(getString(criteria.getInstanceId()));
//        String dataSheetId = JPAUtils.appendWildcard(getString(criteria.getDataSheetId()));
        String bcBirdCriteria = JPAUtils.appendWildcard(criteria.getBCBirdCriteria());

        QBCBirdEntity bcBirdEntity = QBCBirdEntity.bCBirdEntity;
        JPAQuery query = new JPAQuery(em).from(bcBirdEntity);

//        if(instanceId != null) {
//            query.where(bcBirdEntity.instanceId.like(instanceId));
//        }
//
//        if(dataSheetId != null) {
//            query.where(bcBirdEntity.dataSheetId.like(dataSheetId));
//        }

        if(bcBirdCriteria != null) {
            query.where(bcBirdEntity.instanceId.like(bcBirdCriteria).or(bcBirdEntity.dataSheetId.like(bcBirdCriteria)));
        }

        query.orderBy(bcBirdEntity.instanceId.desc());

        PagedResponse<BCBirdEntity> response = JPAUtils.listResults(query, criteria, bcBirdEntity);
        return response;
    }

    private String getString(Integer id) {
        try {
            return id.toString();
        } catch (NullPointerException n) {}
        return new String();
    }
}
