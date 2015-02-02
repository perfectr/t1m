package nz.govt.doc.t1m.services.instance.litterBeach;

import nz.govt.doc.t1m.domain.instance.litterBeach.LBItemEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.domain.response.Response;
import nz.govt.doc.t1m.services.instance.litterBeach.LBItemCriteria;
import nz.govt.doc.t1m.services.instance.litterBeach.LBItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 */
@Component
public class LBItemService {

    @Autowired
    protected LBItemRepository lbItemRepository;

    public PagedResponse<LBItemEntity> findByCriteria(LBItemCriteria criteria) {
        return lbItemRepository.findByCriteria(criteria);
    }

    public Response<LBItemEntity> findLBItemById(Integer lbItemId) {
        return new Response<>(lbItemRepository.findOne(lbItemId));
    }

    @Transactional
    public Response<LBItemEntity> saveLBItem(LBItemEntity lbItemEntity) {
        LBItemEntity lbItemResponse = lbItemRepository.save(lbItemEntity);
        Response res = new Response<>(lbItemResponse);
        return res;
    }

    public void removeLBItem(Integer lbItemId) {
        lbItemRepository.delete(lbItemId);
    }
}
