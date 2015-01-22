package nz.govt.doc.t1m.services.event;

import com.mysema.query.jpa.impl.JPAQuery;
import nz.govt.doc.t1m.domain.event.EventEntity;
import nz.govt.doc.t1m.domain.event.QEventEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.utils.JPAUtils;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This is an example of how to use QueryDSL to create dynamic queries at runtime when the number of input parameters
 * can vary for each request.
 */
@Component
public class EventRepositoryImpl implements EventRepositoryCustom {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public PagedResponse<EventEntity> findByCriteria(EventCriteria criteria) {

        String observer = JPAUtils.appendWildcard(criteria.getObserver());
        String locationId = JPAUtils.appendWildcard(criteria.getLocationId());
        String eventCriteria = JPAUtils.appendWildcard(criteria.getEventCriteria());

        QEventEntity eventEntity = QEventEntity.eventEntity;
        JPAQuery query = new JPAQuery(em).from(eventEntity);

        if(observer != null) {
            query.where(eventEntity.observer.like(observer));
        }

        if(locationId != null) {
            query.where(eventEntity.locationId.like(locationId));
        }

        if(eventCriteria != null) {
            query.where(eventEntity.observer.like(eventCriteria).or(eventEntity.locationId.like(eventCriteria)));
        }

        query.orderBy(eventEntity.locationId.asc(), eventEntity.observer.asc());

        PagedResponse<EventEntity> response = JPAUtils.listResults(query, criteria, eventEntity);
        return response;
    }

}
