package nz.govt.doc.t1m.services.dataSheet.litterBeach;

import nz.govt.doc.t1m.domain.dataSheet.litterBeach.LitterBeachEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.services.dataSheet.litterBeach.LitterBeachCriteria;

/**
 * This "custom" interface is a design pattern to allow the automatically generated Spring JPA class to be extended
 * with custom methods. See the Spring documentation for further details.
 */
public interface LitterBeachRepositoryCustom {

    PagedResponse<LitterBeachEntity> findByCriteria(LitterBeachCriteria criteria);
}
