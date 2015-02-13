package nz.govt.doc.t1m.services.image;

import com.mysema.query.jpa.impl.JPAQuery;
import nz.govt.doc.t1m.domain.image.ImageEntity;
import nz.govt.doc.t1m.domain.image.QImageEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.utils.JPAUtils;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This is an example of how to use QueryDSL to create dynamic queries at runtime when the number of input parameters
 * can vary for each request.
 */
@Component
public class ImageRepositoryImpl implements ImageRepositoryCustom {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public PagedResponse<ImageEntity> findByCriteria(ImageCriteria criteria) {

        String imageName = JPAUtils.appendWildcard(criteria.getImageName());
        String imageCriteria = JPAUtils.appendWildcard(criteria.getImageCriteria());

        QImageEntity imageEntity = QImageEntity.imageEntity;
        JPAQuery query = new JPAQuery(em).from(imageEntity);

        if(imageName != null) {
            query.where(imageEntity.imageName.like(imageName));
        }

        if(imageCriteria != null) {
            query.where(imageEntity.imageName.like(imageCriteria).or(imageEntity.imageId.like(imageCriteria)));
        }

        query.orderBy(imageEntity.imageId.desc());

        PagedResponse<ImageEntity> response = JPAUtils.listResults(query, criteria, imageEntity);
        return response;
    }

}
