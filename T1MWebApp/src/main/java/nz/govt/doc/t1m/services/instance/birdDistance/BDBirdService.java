package nz.govt.doc.t1m.services.instance.birdDistance;

import nz.govt.doc.t1m.domain.instance.birdDistance.BDBirdEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.domain.response.Response;
import nz.govt.doc.t1m.services.instance.birdDistance.BDBirdCriteria;
import nz.govt.doc.t1m.services.instance.birdDistance.BDBirdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 */
@Component
public class BDBirdService {

    @Autowired
    protected BDBirdRepository bdBirdRepository;

    public PagedResponse<BDBirdEntity> findByCriteria(BDBirdCriteria criteria) {
        return bdBirdRepository.findByCriteria(criteria);
    }

    public Response<BDBirdEntity> findBDBirdById(Integer bdBirdId) {
        return new Response<>(bdBirdRepository.findOne(bdBirdId));
    }

    @Transactional
    public Response<BDBirdEntity> saveBDBird(BDBirdEntity bdBirdEntity) {
        BDBirdEntity bdBirdResponse = bdBirdRepository.save(bdBirdEntity);
        Response res = new Response<>(bdBirdResponse);
        return res;
    }

    public void removeBDBird(Integer bdBirdId) {
        bdBirdRepository.delete(bdBirdId);
    }
}
