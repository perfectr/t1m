package nz.govt.doc.t1m.services.dataSheet.litterBeach;

import nz.govt.doc.t1m.domain.dataSheet.litterBeach.LitterBeachEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.domain.response.Response;
import nz.govt.doc.t1m.services.dataSheet.litterBeach.LitterBeachCriteria;
import nz.govt.doc.t1m.services.dataSheet.litterBeach.LitterBeachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 */
@Component
public class LitterBeachService {

    @Autowired
    protected LitterBeachRepository litterBeachRepository;

    public PagedResponse<LitterBeachEntity> findByCriteria(LitterBeachCriteria criteria) {
        return litterBeachRepository.findByCriteria(criteria);
    }

    public Response<LitterBeachEntity> findLitterBeachById(Integer litterBeachId) {
        return new Response<>(litterBeachRepository.findOne(litterBeachId));
    }

//    @Transactional
//    public Response<LitterBeachEntity> saveLitterBeach(LitterBeachEntity litterBeachEntity) {
//        LitterBeachEntity litterBeachResponse = litterBeachRepository.save(litterBeachEntity);
//        Response res = new Response<>(litterBeachResponse);
//        return res;
//    }

    @Transactional
    public LitterBeachEntity saveLitterBeach(LitterBeachEntity litterBeachEntity) {
        LitterBeachEntity litterBeachResponse = litterBeachRepository.save(litterBeachEntity);
        return litterBeachResponse;
    }

    public void removeLitterBeach(Integer litterBeachId) {
        litterBeachRepository.delete(litterBeachId);
    }
}
