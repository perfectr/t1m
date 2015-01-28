package nz.govt.doc.t1m.services.dataSheet;

import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.domain.dataSheet.DataSheetEntity;
import nz.govt.doc.t1m.services.dataSheet.DataSheetCriteria;

/**
 * This "custom" interface is a design pattern to allow the automatically generated Spring JPA class to be extended
 * with custom methods. See the Spring documentation for further details.
 */
public interface DataSheetRepositoryCustom {

    PagedResponse<DataSheetEntity> findByCriteria(DataSheetCriteria criteria);
}
