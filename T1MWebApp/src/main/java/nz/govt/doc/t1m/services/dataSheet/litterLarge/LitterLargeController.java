package nz.govt.doc.t1m.services.dataSheet.litterLarge;

import nz.govt.doc.t1m.domain.dataSheet.litterLarge.LitterLargeEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.domain.response.Response;
import nz.govt.doc.t1m.services.dataSheet.litterLarge.LitterLargeCriteria;
import nz.govt.doc.t1m.services.dataSheet.litterLarge.LitterLargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 */
@RestController
@RequestMapping("/rest/litterLarge")
public class LitterLargeController {

    @Autowired
    protected LitterLargeService litterLargeService;

    @RequestMapping(value = "/{litterLargeId}", method = RequestMethod.GET)
    public Response<LitterLargeEntity> getLitterLarge(@PathVariable(value = "litterLargeId") Integer litterLargeId) {
        return litterLargeService.findLitterLargeById(litterLargeId);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public PagedResponse<LitterLargeEntity> search(@RequestBody LitterLargeCriteria criteria) {
        return litterLargeService.findByCriteria(criteria);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Response<LitterLargeEntity> saveLitterLarge(@RequestBody String json) {
        //return litterLargeService.saveLitterLarge(new LitterLargeEntity());
        System.out.println(json);
        return null;
    }

    @RequestMapping(value = "/{litterLargeId}", method = RequestMethod.DELETE)
    public void removeLitterLarge(@PathVariable(value = "litterLargeId") Integer litterLargeId) {
        litterLargeService.removeLitterLarge(litterLargeId);
    }

}
