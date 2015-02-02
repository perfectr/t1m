package nz.govt.doc.t1m.services.dataSheet.litterBeach;

import nz.govt.doc.t1m.domain.dataSheet.litterBeach.LitterBeachEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.domain.response.Response;
import nz.govt.doc.t1m.services.dataSheet.litterBeach.LitterBeachCriteria;
import nz.govt.doc.t1m.services.dataSheet.litterBeach.LitterBeachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 */
@RestController
@RequestMapping("/rest/litterBeach")
public class LitterBeachController {

    @Autowired
    protected LitterBeachService litterBeachService;

    @RequestMapping(value = "/{litterBeachId}", method = RequestMethod.GET)
    public Response<LitterBeachEntity> getLitterBeach(@PathVariable(value = "litterBeachId") Integer litterBeachId) {
        return litterBeachService.findLitterBeachById(litterBeachId);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public PagedResponse<LitterBeachEntity> search(@RequestBody LitterBeachCriteria criteria) {
        return litterBeachService.findByCriteria(criteria);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Response<LitterBeachEntity> saveLitterBeach(@RequestBody String json) {
        //return litterBeachService.saveLitterBeach(new LitterBeachEntity());
        System.out.println(json);
        return null;
    }

    @RequestMapping(value = "/{litterBeachId}", method = RequestMethod.DELETE)
    public void removeLitterBeach(@PathVariable(value = "litterBeachId") Integer litterBeachId) {
        litterBeachService.removeLitterBeach(litterBeachId);
    }

}
