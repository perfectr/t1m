package nz.govt.doc.t1m.services.instance.litterLarge;

import nz.govt.doc.t1m.domain.instance.litterLarge.LLItemEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.domain.response.Response;
import nz.govt.doc.t1m.services.instance.litterLarge.LLItemCriteria;
import nz.govt.doc.t1m.services.instance.litterLarge.LLItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 */
@Component
public class LLItemService {

    @Autowired
    protected LLItemRepository llItemRepository;

    public PagedResponse<LLItemEntity> findByCriteria(LLItemCriteria criteria) {
        return llItemRepository.findByCriteria(criteria);
    }

    public Response<LLItemEntity> findLLItemById(Integer llItemId) {
        return new Response<>(llItemRepository.findOne(llItemId));
    }

//    @Transactional
//    public Response<LLItemEntity> saveLLItem(LLItemEntity llItemEntity) {
//        LLItemEntity llItemResponse = llItemRepository.save(llItemEntity);
//        Response res = new Response<>(llItemResponse);
//        return res;
//    }

    @Transactional
    public LLItemEntity saveLLItem(LLItemEntity llItemEntity) {
        LLItemEntity llItemResponse = llItemRepository.save(llItemEntity);
        return llItemResponse;
    }

    public void removeLLItem(Integer llItemId) {
        llItemRepository.delete(llItemId);
    }
}
