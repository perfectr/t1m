package nz.govt.doc.t1m.services.dataSheet.incidentalBird;

import nz.govt.doc.t1m.domain.dataSheet.incidentalBird.IncidentalBirdEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.domain.response.Response;
import nz.govt.doc.t1m.services.dataSheet.incidentalBird.IncidentalBirdCriteria;
import nz.govt.doc.t1m.services.dataSheet.incidentalBird.IncidentalBirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 */
@RestController
@RequestMapping("/rest/incidentalBird")
public class IncidentalBirdController {

    @Autowired
    protected IncidentalBirdService incidentalBirdService;

    @RequestMapping(value = "/{incidentalBirdId}", method = RequestMethod.GET)
    public Response<IncidentalBirdEntity> getIncidentalBird(@PathVariable(value = "incidentalBirdId") Integer incidentalBirdId) {
        return incidentalBirdService.findIncidentalBirdById(incidentalBirdId);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public PagedResponse<IncidentalBirdEntity> search(@RequestBody IncidentalBirdCriteria criteria) {
        return incidentalBirdService.findByCriteria(criteria);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Response<IncidentalBirdEntity> saveIncidentalBird(@RequestBody String json) {
        //return incidentalBirdService.saveIncidentalBird(new IncidentalBirdEntity());
        System.out.println(json);
        return null;
    }

    @RequestMapping(value = "/{incidentalBirdId}", method = RequestMethod.DELETE)
    public void removeIncidentalBird(@PathVariable(value = "incidentalBirdId") Integer incidentalBirdId) {
        incidentalBirdService.removeIncidentalBird(incidentalBirdId);
    }

}
