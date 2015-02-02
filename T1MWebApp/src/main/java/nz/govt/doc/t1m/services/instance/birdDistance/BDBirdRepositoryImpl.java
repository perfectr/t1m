package nz.govt.doc.t1m.services.instance.birdDistance;

import com.mysema.query.jpa.impl.JPAQuery;
import nz.govt.doc.t1m.domain.instance.birdDistance.BDBirdEntity;
import nz.govt.doc.t1m.domain.instance.birdDistance.QBDBirdEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.services.instance.birdDistance.BDBirdCriteria;
import nz.govt.doc.t1m.services.instance.birdDistance.BDBirdRepositoryCustom;
import nz.govt.doc.t1m.utils.JPAUtils;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This is an example of how to use QueryDSL to create dynamic queries at runtime when the number of input parameters
 * can vary for each request.
 */
@Component
public class BDBirdRepositoryImpl implements BDBirdRepositoryCustom {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public PagedResponse<BDBirdEntity> findByCriteria(BDBirdCriteria criteria) {

//        String instanceId = JPAUtils.appendWildcard(getString(criteria.getInstanceId()));
//        String dataSheetId = JPAUtils.appendWildcard(getString(criteria.getDataSheetId()));
        String bdBirdCriteria = JPAUtils.appendWildcard(criteria.getBDBirdCriteria());

        QBDBirdEntity bdBirdEntity = QBDBirdEntity.bDBirdEntity;
        JPAQuery query = new JPAQuery(em).from(bdBirdEntity);

//        if(instanceId != null) {
//            query.where(bdBirdEntity.instanceId.like(instanceId));
//        }
//
//        if(dataSheetId != null) {
//            query.where(bdBirdEntity.dataSheetId.like(dataSheetId));
//        }

        if(bdBirdCriteria != null) {
            query.where(bdBirdEntity.instanceId.like(bdBirdCriteria).or(bdBirdEntity.dataSheetId.like(bdBirdCriteria)));
        }

        query.orderBy(bdBirdEntity.instanceId.desc());

        PagedResponse<BDBirdEntity> response = JPAUtils.listResults(query, criteria, bdBirdEntity);
        return response;
    }

    private String getString(Integer id) {
        try {
            return id.toString();
        } catch (NullPointerException n) {}
        return new String();
    }
}
