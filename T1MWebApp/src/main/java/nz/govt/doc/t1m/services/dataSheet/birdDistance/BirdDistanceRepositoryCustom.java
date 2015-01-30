package nz.govt.doc.t1m.services.dataSheet.birdDistance;

import nz.govt.doc.t1m.domain.dataSheet.birdDistance.BirdDistanceEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.services.dataSheet.birdDistance.BirdDistanceCriteria;

/**
 * This "custom" interface is a design pattern to allow the automatically generated Spring JPA class to be extended
 * with custom methods. See the Spring documentation for further details.
 */
public interface BirdDistanceRepositoryCustom {

    PagedResponse<BirdDistanceEntity> findByCriteria(BirdDistanceCriteria criteria);
}
