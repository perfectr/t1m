package nz.govt.doc.t1m.services.dataSheet.birdCount;

import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.domain.response.Response;
import nz.govt.doc.t1m.domain.dataSheet.birdCount.BirdCountEntity;
import nz.govt.doc.t1m.services.incoming.DataForm;
import nz.govt.doc.t1m.services.incoming.DataParser;
import nz.govt.doc.t1m.services.dataSheet.birdCount.BirdCountCriteria;
import nz.govt.doc.t1m.services.dataSheet.birdCount.BirdCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 */
@RestController
@RequestMapping("/rest/birdCount")
public class BirdCountController {

    @Autowired
    protected BirdCountService birdCountService;

    @RequestMapping(value = "/{birdCountId}", method = RequestMethod.GET)
    public Response<BirdCountEntity> getBirdCount(@PathVariable(value = "birdCountId") Integer birdCountId) {
        return birdCountService.findBirdCountById(birdCountId);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public PagedResponse<BirdCountEntity> search(@RequestBody BirdCountCriteria criteria) {
        return birdCountService.findByCriteria(criteria);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Response<BirdCountEntity> saveBirdCount(@RequestBody DataForm dataForm) {
        return birdCountService.saveBirdCount(new BirdCountEntity(-2));
    }

    @RequestMapping(value = "/{birdCountId}", method = RequestMethod.DELETE)
    public void removeBirdCount(@PathVariable(value = "birdCountId") Integer birdCountId) {
        birdCountService.removeBirdCount(birdCountId);
    }

}
