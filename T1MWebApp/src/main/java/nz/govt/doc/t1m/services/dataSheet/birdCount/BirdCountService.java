package nz.govt.doc.t1m.services.dataSheet.birdCount;

import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.domain.response.Response;
import nz.govt.doc.t1m.domain.dataSheet.birdCount.BirdCountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 */
@Component
public class BirdCountService {

    @Autowired
    protected BirdCountRepository birdCountRepository;

    public PagedResponse<BirdCountEntity> findByCriteria(BirdCountCriteria criteria) {
        return birdCountRepository.findByCriteria(criteria);
    }

    public Response<BirdCountEntity> findBirdCountById(Integer birdCountId) {
        return new Response<>(birdCountRepository.findOne(birdCountId));
    }

    @Transactional
    public Response<BirdCountEntity> saveBirdCount(BirdCountEntity birdCountEntity) {
        System.out.println("Saving bird count attached to survey: " + birdCountEntity.getSurveyId());
        BirdCountEntity birdCountResponse = birdCountRepository.save(birdCountEntity);
        System.out.println("Saved bird count with ID: " + birdCountResponse.getDataSheetId());
        Response res = new Response<>(birdCountResponse);
        return res;
    }

    public void removeBirdCount(Integer birdCountId) {
        birdCountRepository.delete(birdCountId);
    }
}
