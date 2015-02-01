package nz.govt.doc.t1m.services.instance.litterBeach;

import nz.govt.doc.t1m.domain.instance.litterBeach.LBItemEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.domain.response.Response;
import nz.govt.doc.t1m.services.instance.litterBeach.LBItemCriteria;
import nz.govt.doc.t1m.services.instance.litterBeach.LBItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 */
@RestController
@RequestMapping("/rest/lbItem")
public class LBItemController {

    @Autowired
    protected LBItemService lbItemService;

    @RequestMapping(value = "/{lbItemId}", method = RequestMethod.GET)
    public Response<LBItemEntity> getLBItem(@PathVariable(value = "lbItemId") Integer lbItemId) {
        return lbItemService.findLBItemById(lbItemId);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public PagedResponse<LBItemEntity> search(@RequestBody LBItemCriteria criteria) {
        return lbItemService.findByCriteria(criteria);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Response<LBItemEntity> saveLBItem(@RequestBody String json) {
        //return lbItemService.saveLBItem(new LBItemEntity());
        System.out.println(json);
        return null;
    }

    @RequestMapping(value = "/{lbItemId}", method = RequestMethod.DELETE)
    public void removeLBItem(@PathVariable(value = "lbItemId") Integer lbItemId) {
        lbItemService.removeLBItem(lbItemId);
    }

}
