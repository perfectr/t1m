package nz.govt.doc.t1m.services.instance.litterBeach;

import com.mysema.query.jpa.impl.JPAQuery;
import nz.govt.doc.t1m.domain.instance.litterBeach.LBItemEntity;
import nz.govt.doc.t1m.domain.instance.litterBeach.QLBItemEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.services.instance.litterBeach.LBItemCriteria;
import nz.govt.doc.t1m.services.instance.litterBeach.LBItemRepositoryCustom;
import nz.govt.doc.t1m.utils.JPAUtils;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This is an example of how to use QueryDSL to create dynamic queries at runtime when the number of input parameters
 * can vary for each request.
 */
@Component
public class LBItemRepositoryImpl implements LBItemRepositoryCustom {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public PagedResponse<LBItemEntity> findByCriteria(LBItemCriteria criteria) {

//        String instanceId = JPAUtils.appendWildcard(getString(criteria.getInstanceId()));
//        String dataSheetId = JPAUtils.appendWildcard(getString(criteria.getDataSheetId()));
        String lbItemCriteria = JPAUtils.appendWildcard(criteria.getLBItemCriteria());

        QLBItemEntity lbItemEntity = QLBItemEntity.lBItemEntity;
        JPAQuery query = new JPAQuery(em).from(lbItemEntity);

//        if(instanceId != null) {
//            query.where(lbItemEntity.instanceId.like(instanceId));
//        }
//
//        if(dataSheetId != null) {
//            query.where(lbItemEntity.dataSheetId.like(dataSheetId));
//        }

        if(lbItemCriteria != null) {
            query.where(lbItemEntity.instanceId.like(lbItemCriteria).or(lbItemEntity.dataSheetId.like(lbItemCriteria)));
        }

        query.orderBy(lbItemEntity.instanceId.desc());

        PagedResponse<LBItemEntity> response = JPAUtils.listResults(query, criteria, lbItemEntity);
        return response;
    }

    private String getString(Integer id) {
        try {
            return id.toString();
        } catch (NullPointerException n) {}
        return new String();
    }
}
