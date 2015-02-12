package nz.govt.doc.t1m.services.image;

import nz.govt.doc.t1m.domain.image.ImageEntity;
import nz.govt.doc.t1m.domain.response.PagedResponse;
import nz.govt.doc.t1m.domain.response.Response;
import nz.govt.doc.t1m.services.incoming.ImageForm;
import nz.govt.doc.t1m.services.incoming.ImageParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 */
@Component
public class ImageService {

    @Autowired
    protected ImageRepository imageRepository;

    @Autowired
    protected ImageParser imageParser;

    public PagedResponse<ImageEntity> findByCriteria(ImageCriteria criteria) {
        return imageRepository.findByCriteria(criteria);
    }

    public Response<ImageEntity> findImageById(Integer imageId) {
        return new Response<>(imageRepository.findOne(imageId));
    }

    @Transactional
    public Response<ImageEntity> saveImage(ImageForm imageForm) {
        ImageEntity imageResponse = imageRepository.save(imageParser.saveImage(imageForm));
        Response res = new Response<>(imageResponse);
        return res;
    }

    @Transactional
    public ImageEntity saveImage(ImageEntity imageEntity) {
        ImageEntity imageResponse = imageRepository.save(imageEntity);
        return imageResponse;
    }

    public void removeImage(Integer imageId) {
        imageRepository.delete(imageId);
    }
}
