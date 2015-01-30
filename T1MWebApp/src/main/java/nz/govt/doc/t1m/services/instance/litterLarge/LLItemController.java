package nz.govt.doc.t1m.services.instance.litterLarge;

import nz.govt.doc.t1m.domain.instance.litterLarge.LLItemEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.domain.response.Response;
import nz.govt.doc.t1m.services.instance.litterLarge.LLItemCriteria;
import nz.govt.doc.t1m.services.instance.litterLarge.LLItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 */
@RestController
@RequestMapping("/rest/llItem")
public class LLItemController {

    @Autowired
    protected LLItemService llItemService;

    @RequestMapping(value = "/{llItemId}", method = RequestMethod.GET)
    public Response<LLItemEntity> getLLItem(@PathVariable(value = "llItemId") Integer llItemId) {
        return llItemService.findLLItemById(llItemId);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public PagedResponse<LLItemEntity> search(@RequestBody LLItemCriteria criteria) {
        return llItemService.findByCriteria(criteria);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Response<LLItemEntity> saveLLItem(@RequestBody String json) {
        //return llItemService.saveLLItem(new LLItemEntity());
        System.out.println(json);
        return null;
    }

    @RequestMapping(value = "/{llItemId}", method = RequestMethod.DELETE)
    public void removeLLItem(@PathVariable(value = "llItemId") Integer llItemId) {
        llItemService.removeLLItem(llItemId);
    }

}
