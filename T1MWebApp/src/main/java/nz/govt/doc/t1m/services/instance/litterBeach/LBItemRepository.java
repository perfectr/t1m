package nz.govt.doc.t1m.services.instance.litterBeach;

import nz.govt.doc.t1m.domain.instance.litterBeach.LBItemEntity;
import nz.govt.doc.t1m.services.instance.litterBeach.LBItemRepositoryCustom;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * This uses the Spring Data JPA Repository design pattern. By extending PagingAndSortingRepository (which extends
 * CrudRepository) you automatically get a bunch of created/read/update/delete methods for free. It's also possible
 * to create various simple query method just by using a method naming convention. See the Spring documentation for
 * further details.
 */
public interface LBItemRepository extends PagingAndSortingRepository<LBItemEntity, Integer>, LBItemRepositoryCustom {

    LBItemEntity findOneByInstanceId(Integer instanceId);
}
