package nz.govt.doc.t1m.services.event;

import nz.govt.doc.t1m.domain.event.EventEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.domain.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 */
@Component
public class EventService {

    @Autowired
    protected EventRepository eventRepository;

    public PagedResponse<EventEntity> findByCriteria(EventCriteria criteria) {
        return eventRepository.findByCriteria(criteria);
    }

    public Response<EventEntity> findEventById(Integer eventId) {
        return new Response<>(eventRepository.findOne(eventId));
    }

    @Transactional
    public Response<EventEntity> saveEvent(EventEntity event) {
        EventEntity eventResponse = eventRepository.save(event);
        return new Response<>(eventResponse);
    }

    public void removeEvent(Integer eventId) {
        eventRepository.delete(eventId);
    }
}
