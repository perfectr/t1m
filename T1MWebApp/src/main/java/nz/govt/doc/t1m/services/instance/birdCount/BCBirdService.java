package nz.govt.doc.t1m.services.instance.birdCount;

import nz.govt.doc.t1m.domain.instance.birdCount.BCBirdEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.domain.response.Response;
import nz.govt.doc.t1m.services.instance.birdCount.BCBirdCriteria;
import nz.govt.doc.t1m.services.instance.birdCount.BCBirdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 */
@Component
public class BCBirdService {

    @Autowired
    protected BCBirdRepository bcBirdRepository;

    public PagedResponse<BCBirdEntity> findByCriteria(BCBirdCriteria criteria) {
        return bcBirdRepository.findByCriteria(criteria);
    }

    public Response<BCBirdEntity> findBCBirdById(Integer bcBirdId) {
        return new Response<>(bcBirdRepository.findOne(bcBirdId));
    }

    @Transactional
    public Response<BCBirdEntity> saveBCBird(BCBirdEntity bcBirdEntity) {
        BCBirdEntity bcBirdResponse = bcBirdRepository.save(bcBirdEntity);
        Response res = new Response<>(bcBirdResponse);
        return res;
    }

    public void removeBCBird(Integer bcBirdId) {
        bcBirdRepository.delete(bcBirdId);
    }
}
