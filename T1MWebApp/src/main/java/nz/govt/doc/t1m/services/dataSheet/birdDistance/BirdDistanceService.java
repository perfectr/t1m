package nz.govt.doc.t1m.services.dataSheet.birdDistance;

import nz.govt.doc.t1m.domain.dataSheet.birdDistance.BirdDistanceEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.domain.response.Response;
import nz.govt.doc.t1m.services.dataSheet.birdDistance.BirdDistanceCriteria;
import nz.govt.doc.t1m.services.dataSheet.birdDistance.BirdDistanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 */
@Component
public class BirdDistanceService {

    @Autowired
    protected BirdDistanceRepository birdDistanceRepository;

    public PagedResponse<BirdDistanceEntity> findByCriteria(BirdDistanceCriteria criteria) {
        return birdDistanceRepository.findByCriteria(criteria);
    }

    public Response<BirdDistanceEntity> findBirdDistanceById(Integer birdDistanceId) {
        return new Response<>(birdDistanceRepository.findOne(birdDistanceId));
    }

//    @Transactional
//    public Response<BirdDistanceEntity> saveBirdDistance(BirdDistanceEntity birdDistanceEntity) {
//        BirdDistanceEntity birdDistanceResponse = birdDistanceRepository.save(birdDistanceEntity);
//        Response res = new Response<>(birdDistanceResponse);
//        return res;
//    }

    @Transactional
    public BirdDistanceEntity saveBirdDistance(BirdDistanceEntity birdDistanceEntity) {
        BirdDistanceEntity birdDistanceResponse = birdDistanceRepository.save(birdDistanceEntity);
        return birdDistanceResponse;
    }

    public void removeBirdDistance(Integer birdDistanceId) {
        birdDistanceRepository.delete(birdDistanceId);
    }
}
