package nz.govt.doc.t1m.services.dataSheet.incidentalBird;

import nz.govt.doc.t1m.domain.dataSheet.incidentalBird.IncidentalBirdEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.domain.response.Response;
import nz.govt.doc.t1m.services.dataSheet.incidentalBird.IncidentalBirdCriteria;
import nz.govt.doc.t1m.services.dataSheet.incidentalBird.IncidentalBirdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 */
@Component
public class IncidentalBirdService {

    @Autowired
    protected IncidentalBirdRepository incidentalBirdRepository;

    public PagedResponse<IncidentalBirdEntity> findByCriteria(IncidentalBirdCriteria criteria) {
        return incidentalBirdRepository.findByCriteria(criteria);
    }

    public Response<IncidentalBirdEntity> findIncidentalBirdById(Integer incidentalBirdId) {
        return new Response<>(incidentalBirdRepository.findOne(incidentalBirdId));
    }

    @Transactional
    public Response<IncidentalBirdEntity> saveIncidentalBird(IncidentalBirdEntity incidentalBirdEntity) {
        IncidentalBirdEntity incidentalBirdResponse = incidentalBirdRepository.save(incidentalBirdEntity);
        Response res = new Response<>(incidentalBirdResponse);
        return res;
    }

    public void removeIncidentalBird(Integer incidentalBirdId) {
        incidentalBirdRepository.delete(incidentalBirdId);
    }
}
