package nz.govt.doc.t1m.services.survey;

import nz.govt.doc.t1m.domain.survey.SurveyEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;

/**
 * This "custom" interface is a design pattern to allow the automatically generated Spring JPA class to be extended
 * with custom methods. See the Spring documentation for further details.
 */
public interface SurveyRepositoryCustom {

    PagedResponse<SurveyEntity> findByCriteria(SurveyCriteria criteria);
}
