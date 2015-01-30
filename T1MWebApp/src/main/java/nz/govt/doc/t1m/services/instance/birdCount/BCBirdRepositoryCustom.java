package nz.govt.doc.t1m.services.instance.birdCount;

import nz.govt.doc.t1m.domain.instance.birdCount.BCBirdEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.services.instance.birdCount.BCBirdCriteria;

/**
 * This "custom" interface is a design pattern to allow the automatically generated Spring JPA class to be extended
 * with custom methods. See the Spring documentation for further details.
 */
public interface BCBirdRepositoryCustom {

    PagedResponse<BCBirdEntity> findByCriteria(BCBirdCriteria criteria);
}
