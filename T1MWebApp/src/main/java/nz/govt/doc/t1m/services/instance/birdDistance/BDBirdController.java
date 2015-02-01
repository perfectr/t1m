package nz.govt.doc.t1m.services.instance.birdDistance;

import nz.govt.doc.t1m.domain.instance.birdDistance.BDBirdEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.domain.response.Response;
import nz.govt.doc.t1m.services.instance.birdDistance.BDBirdCriteria;
import nz.govt.doc.t1m.services.instance.birdDistance.BDBirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 */
@RestController
@RequestMapping("/rest/bdBird")
public class BDBirdController {

    @Autowired
    protected BDBirdService bdBirdService;

    @RequestMapping(value = "/{bdBirdId}", method = RequestMethod.GET)
    public Response<BDBirdEntity> getBDBird(@PathVariable(value = "bdBirdId") Integer bdBirdId) {
        return bdBirdService.findBDBirdById(bdBirdId);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public PagedResponse<BDBirdEntity> search(@RequestBody BDBirdCriteria criteria) {
        return bdBirdService.findByCriteria(criteria);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Response<BDBirdEntity> saveBDBird(@RequestBody String json) {
        //return bdBirdService.saveBDBird(new BDBirdEntity());
        System.out.println(json);
        return null;
    }

    @RequestMapping(value = "/{bdBirdId}", method = RequestMethod.DELETE)
    public void removeBDBird(@PathVariable(value = "bdBirdId") Integer bdBirdId) {
        bdBirdService.removeBDBird(bdBirdId);
    }

}
