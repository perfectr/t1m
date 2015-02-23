package nz.govt.doc.t1m.services.incoming;

import nz.govt.doc.t1m.domain.image.ImageEntity;
import nz.govt.doc.t1m.services.image.ImageService;
import nz.govt.doc.t1m.services.incoming.ImageForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * parses data from JSON strings to an image entity for storage
 */
@Component
public class ImageParser {

    @Autowired
    protected ImageService imageService;

    /**
     * parser method for testing images, not used by full implementation
     * @param imageForm
     * @return the completed testing entity
     */
    public ImageEntity saveImage(ImageForm imageForm) {
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setInstanceId(-1);
        imageEntity.setInstanceType("testType");
        imageEntity.setImage(imageForm.getImg().getBytes());
        System.out.println("Length: " + imageEntity.getImage().length);
        return imageEntity;
    }

    /**
     * parser method for the final implementation
     * @param id
     * @param type
     * @param image
     * @return the completed entity
     */
    public ImageEntity saveImage(Integer id, String type, String image) {
        System.out.println("New image found");
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setInstanceId(id);
        imageEntity.setInstanceType(type);
        imageEntity.setImageName(type + " (" + id + ")");
        imageEntity.setImage(image.getBytes());
        ImageEntity res = imageService.saveImage(imageEntity);
        return imageEntity;
    }

}
