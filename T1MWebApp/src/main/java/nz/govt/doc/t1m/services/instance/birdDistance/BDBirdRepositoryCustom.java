package nz.govt.doc.t1m.services.instance.birdDistance;

import nz.govt.doc.t1m.domain.instance.birdDistance.BDBirdEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.services.instance.birdDistance.BDBirdCriteria;

/**
 * This "custom" interface is a design pattern to allow the automatically generated Spring JPA class to be extended
 * with custom methods. See the Spring documentation for further details.
 */
public interface BDBirdRepositoryCustom {

    PagedResponse<BDBirdEntity> findByCriteria(BDBirdCriteria criteria);
}
