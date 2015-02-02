package nz.govt.doc.t1m.services.dataSheet.incidentalBird;

import nz.govt.doc.t1m.domain.dataSheet.incidentalBird.IncidentalBirdEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.services.dataSheet.incidentalBird.IncidentalBirdCriteria;

/**
 * This "custom" interface is a design pattern to allow the automatically generated Spring JPA class to be extended
 * with custom methods. See the Spring documentation for further details.
 */
public interface IncidentalBirdRepositoryCustom {

    PagedResponse<IncidentalBirdEntity> findByCriteria(IncidentalBirdCriteria criteria);
}
