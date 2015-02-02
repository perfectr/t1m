package nz.govt.doc.t1m.services.dataSheet.beachCharacterization;

import nz.govt.doc.t1m.domain.dataSheet.beachCharacterization.BeachCharacterizationEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.domain.response.Response;
import nz.govt.doc.t1m.services.dataSheet.beachCharacterization.BeachCharacterizationCriteria;
import nz.govt.doc.t1m.services.dataSheet.beachCharacterization.BeachCharacterizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 */
@RestController
@RequestMapping("/rest/beachCharacterization")
public class BeachCharacterizationController {

    @Autowired
    protected BeachCharacterizationService beachCharacterizationService;

    @RequestMapping(value = "/{beachCharacterizationId}", method = RequestMethod.GET)
    public Response<BeachCharacterizationEntity> getBeachCharacterization(@PathVariable(value = "beachCharacterizationId") Integer beachCharacterizationId) {
        return beachCharacterizationService.findBeachCharacterizationById(beachCharacterizationId);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public PagedResponse<BeachCharacterizationEntity> search(@RequestBody BeachCharacterizationCriteria criteria) {
        return beachCharacterizationService.findByCriteria(criteria);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Response<BeachCharacterizationEntity> saveBeachCharacterization(@RequestBody String json) {
        //return beachCharacterizationService.saveBeachCharacterization(new BeachCharacterizationEntity());
        System.out.println(json);
        return null;
    }

    @RequestMapping(value = "/{beachCharacterizationId}", method = RequestMethod.DELETE)
    public void removeBeachCharacterization(@PathVariable(value = "beachCharacterizationId") Integer beachCharacterizationId) {
        beachCharacterizationService.removeBeachCharacterization(beachCharacterizationId);
    }

}
