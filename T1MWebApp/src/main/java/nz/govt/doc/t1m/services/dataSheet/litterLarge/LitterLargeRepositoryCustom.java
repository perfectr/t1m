package nz.govt.doc.t1m.services.dataSheet.litterLarge;

import nz.govt.doc.t1m.domain.dataSheet.litterLarge.LitterLargeEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.services.dataSheet.litterLarge.LitterLargeCriteria;

/**
 * This "custom" interface is a design pattern to allow the automatically generated Spring JPA class to be extended
 * with custom methods. See the Spring documentation for further details.
 */
public interface LitterLargeRepositoryCustom {

    PagedResponse<LitterLargeEntity> findByCriteria(LitterLargeCriteria criteria);
}
