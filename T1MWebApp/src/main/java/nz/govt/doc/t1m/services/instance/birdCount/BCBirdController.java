package nz.govt.doc.t1m.services.instance.birdCount;

import nz.govt.doc.t1m.domain.instance.birdCount.BCBirdEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.domain.response.Response;
import nz.govt.doc.t1m.services.instance.birdCount.BCBirdCriteria;
import nz.govt.doc.t1m.services.instance.birdCount.BCBirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 */
@RestController
@RequestMapping("/rest/bcBird")
public class BCBirdController {

    @Autowired
    protected BCBirdService bcBirdService;

    @RequestMapping(value = "/{bcBirdId}", method = RequestMethod.GET)
    public Response<BCBirdEntity> getBCBird(@PathVariable(value = "bcBirdId") Integer bcBirdId) {
        return bcBirdService.findBCBirdById(bcBirdId);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public PagedResponse<BCBirdEntity> search(@RequestBody BCBirdCriteria criteria) {
        return bcBirdService.findByCriteria(criteria);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Response<BCBirdEntity> saveBCBird(@RequestBody Response<BCBirdEntity> request) {
        return bcBirdService.saveBCBird(request.getModel());
    }

    @RequestMapping(value = "/{bcBirdId}", method = RequestMethod.DELETE)
    public void removeBCBird(@PathVariable(value = "bcBirdId") Integer bcBirdId) {
        bcBirdService.removeBCBird(bcBirdId);
    }

}
