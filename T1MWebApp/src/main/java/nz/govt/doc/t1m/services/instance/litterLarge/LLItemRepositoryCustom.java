package nz.govt.doc.t1m.services.instance.litterLarge;

import nz.govt.doc.t1m.domain.instance.litterLarge.LLItemEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.services.instance.litterLarge.LLItemCriteria;

/**
 * This "custom" interface is a design pattern to allow the automatically generated Spring JPA class to be extended
 * with custom methods. See the Spring documentation for further details.
 */
public interface LLItemRepositoryCustom {

    PagedResponse<LLItemEntity> findByCriteria(LLItemCriteria criteria);
}
