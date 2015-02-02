package nz.govt.doc.t1m.services.dataSheet.beachCharacterization;

import nz.govt.doc.t1m.domain.dataSheet.beachCharacterization.BeachCharacterizationEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.services.dataSheet.beachCharacterization.BeachCharacterizationCriteria;

/**
 * This "custom" interface is a design pattern to allow the automatically generated Spring JPA class to be extended
 * with custom methods. See the Spring documentation for further details.
 */
public interface BeachCharacterizationRepositoryCustom {

    PagedResponse<BeachCharacterizationEntity> findByCriteria(BeachCharacterizationCriteria criteria);
}
