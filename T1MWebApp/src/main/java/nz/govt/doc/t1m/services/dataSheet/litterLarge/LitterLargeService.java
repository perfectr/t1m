package nz.govt.doc.t1m.services.dataSheet.litterLarge;

import nz.govt.doc.t1m.domain.dataSheet.litterLarge.LitterLargeEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.domain.response.Response;
import nz.govt.doc.t1m.services.dataSheet.litterLarge.LitterLargeCriteria;
import nz.govt.doc.t1m.services.dataSheet.litterLarge.LitterLargeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 */
@Component
public class LitterLargeService {

    @Autowired
    protected LitterLargeRepository litterLargeRepository;

    public PagedResponse<LitterLargeEntity> findByCriteria(LitterLargeCriteria criteria) {
        return litterLargeRepository.findByCriteria(criteria);
    }

    public Response<LitterLargeEntity> findLitterLargeById(Integer litterLargeId) {
        return new Response<>(litterLargeRepository.findOne(litterLargeId));
    }

//    @Transactional
//    public Response<LitterLargeEntity> saveLitterLarge(LitterLargeEntity litterLargeEntity) {
//        LitterLargeEntity litterLargeResponse = litterLargeRepository.save(litterLargeEntity);
//        Response res = new Response<>(litterLargeResponse);
//        return res;
//    }

    @Transactional
    public LitterLargeEntity saveLitterLarge(LitterLargeEntity litterLargeEntity) {
        LitterLargeEntity litterLargeResponse = litterLargeRepository.save(litterLargeEntity);
        return litterLargeResponse;
    }

    public void removeLitterLarge(Integer litterLargeId) {
        litterLargeRepository.delete(litterLargeId);
    }
}
