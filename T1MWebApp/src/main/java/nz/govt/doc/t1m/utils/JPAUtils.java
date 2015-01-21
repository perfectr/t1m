package nz.govt.doc.t1m.utils;

import com.mysema.query.SearchResults;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.Expression;
import nz.govt.doc.t1m.domain.criteria.PagedCriteria;
import nz.govt.doc.t1m.domain.response.PagedResponse;

/**
 */
public class JPAUtils {

    /**
     * Helper method to convert a query and page criteria into a paged response. There may need to be other flavours
     * of this method in different situations, or just code it directly in the data access objects.
     *
     * @param query
     * @param pagedCriteria
     * @param expression
     * @param <T>
     * @return
     */
    public static <T> PagedResponse<T> listResults(JPAQuery query, PagedCriteria pagedCriteria, Expression<T> expression) {
        SearchResults<T> searchResults = query.offset(pagedCriteria.getOffset()).limit(pagedCriteria.getPageSize()).listResults(expression);
        PagedResponse<T> response = new PagedResponse<>(searchResults);
        return response;
    }

    public static String appendWildcard(String criteriaValue) {
        if(criteriaValue != null && !criteriaValue.endsWith("%")) {
            criteriaValue += "%";
        }
        return criteriaValue;
    }
}
