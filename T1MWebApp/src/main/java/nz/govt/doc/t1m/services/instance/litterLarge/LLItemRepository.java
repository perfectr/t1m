package nz.govt.doc.t1m.services.instance.litterLarge;

import nz.govt.doc.t1m.domain.instance.litterLarge.LLItemEntity;
import nz.govt.doc.t1m.services.instance.litterLarge.LLItemRepositoryCustom;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * This uses the Spring Data JPA Repository design pattern. By extending PagingAndSortingRepository (which extends
 * CrudRepository) you automatically get a bunch of created/read/update/delete methods for free. It's also possible
 * to create various simple query method just by using a method naming convention. See the Spring documentation for
 * further details.
 */
public interface LLItemRepository extends PagingAndSortingRepository<LLItemEntity, Integer>, LLItemRepositoryCustom {

    LLItemEntity findOneByInstanceId(Integer instanceId);
}
