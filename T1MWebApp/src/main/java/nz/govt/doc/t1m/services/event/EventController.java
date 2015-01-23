package nz.govt.doc.t1m.services.event;

import nz.govt.doc.t1m.domain.event.EventEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.domain.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 */
@RestController
@RequestMapping("/rest/event")
public class EventController {

    @Autowired
    protected EventService eventService;

    @RequestMapping(value = "/{eventId}", method = RequestMethod.GET)
    public Response<EventEntity> getEvent(@PathVariable(value = "eventId") Integer eventId) {
        return eventService.findEventById(eventId);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public PagedResponse<EventEntity> search(@RequestBody EventCriteria criteria) {
        return eventService.findByCriteria(criteria);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Response<EventEntity> saveEvent(@RequestBody DataForm dataForm) {
        return eventService.saveEvent(dataForm.getEE());
    }

    @RequestMapping(value = "/{eventId}", method = RequestMethod.DELETE)
    public void removeEvent(@PathVariable(value = "eventId") Integer eventId) {
        eventService.removeEvent(eventId);
    }

}
