package nz.govt.doc.t1m.services.instance.litterLarge;

import com.mysema.query.jpa.impl.JPAQuery;
import nz.govt.doc.t1m.domain.instance.litterLarge.LLItemEntity;
import nz.govt.doc.t1m.domain.instance.litterLarge.QLLItemEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.services.instance.litterLarge.LLItemCriteria;
import nz.govt.doc.t1m.services.instance.litterLarge.LLItemRepositoryCustom;
import nz.govt.doc.t1m.utils.JPAUtils;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This is an example of how to use QueryDSL to create dynamic queries at runtime when the number of input parameters
 * can vary for each request.
 */
@Component
public class LLItemRepositoryImpl implements LLItemRepositoryCustom {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public PagedResponse<LLItemEntity> findByCriteria(LLItemCriteria criteria) {

//        String instanceId = JPAUtils.appendWildcard(getString(criteria.getInstanceId()));
//        String dataSheetId = JPAUtils.appendWildcard(getString(criteria.getDataSheetId()));
        String llItemCriteria = JPAUtils.appendWildcard(criteria.getLLItemCriteria());

        QLLItemEntity llItemEntity = QLLItemEntity.lLItemEntity;
        JPAQuery query = new JPAQuery(em).from(llItemEntity);

//        if(instanceId != null) {
//            query.where(llItemEntity.instanceId.like(instanceId));
//        }
//
//        if(dataSheetId != null) {
//            query.where(llItemEntity.dataSheetId.like(dataSheetId));
//        }

        if(llItemCriteria != null) {
            query.where(llItemEntity.instanceId.like(llItemCriteria).or(llItemEntity.dataSheetId.like(llItemCriteria)));
        }

        query.orderBy(llItemEntity.instanceId.desc());

        PagedResponse<LLItemEntity> response = JPAUtils.listResults(query, criteria, llItemEntity);
        return response;
    }

    private String getString(Integer id) {
        try {
            return id.toString();
        } catch (NullPointerException n) {}
        return new String();
    }
}
