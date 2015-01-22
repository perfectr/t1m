package nz.govt.doc.t1m.services.event;

import nz.govt.doc.t1m.domain.event.EventEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;

/**
 * This "custom" interface is a design pattern to allow the automatically generated Spring JPA class to be extended
 * with custom methods. See the Spring documentation for further details.
 */
public interface EventRepositoryCustom {

    PagedResponse<EventEntity> findByCriteria(EventCriteria criteria);
}
