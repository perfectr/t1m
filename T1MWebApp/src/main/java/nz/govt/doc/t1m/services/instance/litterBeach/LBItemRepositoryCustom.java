package nz.govt.doc.t1m.services.instance.litterBeach;

import nz.govt.doc.t1m.domain.instance.litterBeach.LBItemEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.services.instance.litterBeach.LBItemCriteria;

/**
 * This "custom" interface is a design pattern to allow the automatically generated Spring JPA class to be extended
 * with custom methods. See the Spring documentation for further details.
 */
public interface LBItemRepositoryCustom {

    PagedResponse<LBItemEntity> findByCriteria(LBItemCriteria criteria);
}
