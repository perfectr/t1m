package nz.govt.doc.t1m.services.dataSheet.beachCharacterization;

import nz.govt.doc.t1m.domain.dataSheet.beachCharacterization.BeachCharacterizationEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.domain.response.Response;
import nz.govt.doc.t1m.services.dataSheet.beachCharacterization.BeachCharacterizationCriteria;
import nz.govt.doc.t1m.services.dataSheet.beachCharacterization.BeachCharacterizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 */
@Component
public class BeachCharacterizationService {

    @Autowired
    protected BeachCharacterizationRepository beachCharacterizationRepository;

    public PagedResponse<BeachCharacterizationEntity> findByCriteria(BeachCharacterizationCriteria criteria) {
        return beachCharacterizationRepository.findByCriteria(criteria);
    }

    public Response<BeachCharacterizationEntity> findBeachCharacterizationById(Integer beachCharacterizationId) {
        return new Response<>(beachCharacterizationRepository.findOne(beachCharacterizationId));
    }

    @Transactional
    public Response<BeachCharacterizationEntity> saveBeachCharacterization(BeachCharacterizationEntity beachCharacterizationEntity) {
        BeachCharacterizationEntity beachCharacterizationResponse = beachCharacterizationRepository.save(beachCharacterizationEntity);
        Response res = new Response<>(beachCharacterizationResponse);
        return res;
    }

    public void removeBeachCharacterization(Integer beachCharacterizationId) {
        beachCharacterizationRepository.delete(beachCharacterizationId);
    }
}
