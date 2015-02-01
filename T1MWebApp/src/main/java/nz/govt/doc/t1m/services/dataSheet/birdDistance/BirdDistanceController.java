package nz.govt.doc.t1m.services.dataSheet.birdDistance;

import nz.govt.doc.t1m.domain.dataSheet.birdDistance.BirdDistanceEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.domain.response.Response;
import nz.govt.doc.t1m.services.dataSheet.birdDistance.BirdDistanceCriteria;
import nz.govt.doc.t1m.services.dataSheet.birdDistance.BirdDistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 */
@RestController
@RequestMapping("/rest/birdDistance")
public class BirdDistanceController {

    @Autowired
    protected BirdDistanceService birdDistanceService;

    @RequestMapping(value = "/{birdDistanceId}", method = RequestMethod.GET)
    public Response<BirdDistanceEntity> getBirdDistance(@PathVariable(value = "birdDistanceId") Integer birdDistanceId) {
        return birdDistanceService.findBirdDistanceById(birdDistanceId);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public PagedResponse<BirdDistanceEntity> search(@RequestBody BirdDistanceCriteria criteria) {
        return birdDistanceService.findByCriteria(criteria);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Response<BirdDistanceEntity> saveBirdDistance(@RequestBody String json) {
        //return birdDistanceService.saveBirdDistance(new BirdDistanceEntity());
        System.out.println(json);
        return null;
    }

    @RequestMapping(value = "/{birdDistanceId}", method = RequestMethod.DELETE)
    public void removeBirdDistance(@PathVariable(value = "birdDistanceId") Integer birdDistanceId) {
        birdDistanceService.removeBirdDistance(birdDistanceId);
    }

}
